package jp.satoshun.andmongodemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.satoshun.andmongo.Cursor;
import jp.co.satoshun.andmongo.MongoClient;
import jp.co.satoshun.andmongo.MongoCollection;
import jp.co.satoshun.andmongo.MongoDatabase;

public class MainActivity extends Activity {
    MongoClient client;
    MongoDatabase db;
    MongoCollection col;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new MongoClient(getApplicationContext());
        db = client.getDB("test");
        col = db.getCollection("colname");

        saveQuery();
        findQuery();
    }

    private void saveQuery() {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("food", "rice");
        query.put("tomorrow", "so happy");

        col.save(query);
    }

    private void findQuery() {
        List<String> query = new ArrayList<String>();
        query.add("food");
        query.add("tomorrow");

        Cursor cursor = col.find(query);
        List<String> result = cursor.get();
        TextView food = (TextView) findViewById(R.id.food);
        TextView tomorrow = (TextView) findViewById(R.id.tomorrow);
        food.setText(result.get(0));
        tomorrow.setText(result.get(1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
