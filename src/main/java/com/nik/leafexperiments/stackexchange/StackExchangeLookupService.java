package com.nik.leafexperiments.stackexchange;

import com.nik.leafexperiments.entity.Question;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;

public class StackExchangeLookupService {

    public StackExchangeLookupService(ArrayList<Question> questions, String str, String fromDate, String toDate) {
        try {
            if (!fromDate.isEmpty()) fromDate = convertInputDate(fromDate + " 00:00:00");
            if (!toDate.isEmpty()) toDate = convertInputDate(toDate + " 23:59:59");
            fillQuestionsList(questions, str, fromDate, toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public long convertDateToEpoch(String date) throws ParseException {
        long epoch = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                .parse(date).getTime() / 1000;
        return epoch;
    }

    public String convertEpochToDate(long epoch) {
        String date = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                .format(new java.util.Date(epoch * 1000));
        return date;
    }

    public String createLink(String str, String fromDate, String toDate) throws ParseException, UnsupportedEncodingException {
        String query = URLEncoder.encode(str, "UTF-8");
        if (fromDate.isEmpty() || toDate.isEmpty()) {
            if (fromDate.isEmpty() && toDate.isEmpty())
                return "http://api.stackexchange.com/2.2/search?pagesize=100&order=desc&sort=creation&intitle=" +
                        query + "&site=stackoverflow";
            if (fromDate.isEmpty())
                return "http://api.stackexchange.com/2.2/search?pagesize=100&todate=" + convertDateToEpoch(toDate +
                        " 23:59:59") + "&order=desc&sort=creation&intitle=" + query + "&site=stackoverflow";
            if (toDate.isEmpty())
                return "http://api.stackexchange.com/2.2/search?pagesize=100&fromdate=" + convertDateToEpoch(fromDate +
                        " 00:00:00") + "&order=desc&sort=creation&intitle=" + query + "&site=stackoverflow";
        }
        return "http://api.stackexchange.com/2.2/search?pagesize=100&fromdate=" +
                convertDateToEpoch(fromDate + " 00:00:00") + "&todate=" + convertDateToEpoch(toDate + " 23:59:59") +
                "&order=desc&sort=creation&intitle=" + query + "&site=stackoverflow";
    }

    public void fillQuestionsList(ArrayList<Question> questions, String str, String fromDate, String toDate)
            throws ParseException, IOException, JSONException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(createLink(str, fromDate, toDate));
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        StringBuilder sb = new StringBuilder();
        if (entity != null) {
            try (InputStream stream = entity.getContent()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }
        }
        JSONObject jsonResponse = new JSONObject(sb.toString());
        JSONArray jsonArray = jsonResponse.getJSONArray("items");
        Question question = new Question();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject temp = jsonArray.getJSONObject(i);
            question = new Question();
            question.setTitle(temp.getString("title"));
            question.setId(temp.getInt("question_id"));
            question.setLink(temp.getString("link"));
            question.setCreator(temp.getJSONObject("owner").getString("display_name"));
            question.setAnswered(temp.getBoolean("is_answered"));
            question.setCreationDate(convertEpochToDate(temp.getLong("creation_date")));
            question.setAnswersCount(temp.getInt("answer_count"));
            questions.add(question);
        }

        if (jsonArray.length() == 100)
            fillQuestionsList(questions, str, fromDate, question.getCreationDate());
    }

    public String convertInputDate(String date) throws ParseException {
        long epoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
                .parse(date).getTime() / 1000;
        return convertEpochToDate(epoch);
    }
}
