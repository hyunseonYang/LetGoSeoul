package com.example.test.letsgoseoul;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import static com.example.test.letsgoseoul.R.id.listView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(listView);
        String[] hp = {"1.  명동     ( Myeongdong )",
                "2.  홍대     ( Hongdae )",
                "3.  종로     ( Jongro )",
                "4.  안산     ( Ansan )",
                "5.  상록수  ( Sangloksu )",
                "6.  동대문  ( Dongdaemun )",
                "7.  신촌     ( Sinchon )",
                "8.  건대     ( GUENDAE )",
                "9.  노원     ( Nowon )",
                "10. 강남     ( Gangnam )"};
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_list_item, R.id.tv_hotPlace, hp);
        lv.setTextFilterEnabled(true);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object vo = parent.getAdapter().getItem(position);
                Intent intent = new Intent(MainActivity.this, Selected_Place.class);
                intent.putExtra("hotPlace",parent.getItemIdAtPosition(position));
                //담을 데이터가 무엇일까~~~요?
                startActivity(intent);
                Toast.makeText(MainActivity.this,"리스트클릭",Toast.LENGTH_LONG).show();
            }
        });
        /*Button nearBtn = (Button) findViewById(R.id.near);
        nearBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            }
        });*/


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search Hot Place");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //검색버튼 클릭시
                Intent intent = new Intent(getApplicationContext(),Selected_Place.class);
                intent.putExtra("hotPlace",query);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "[검색버튼클릭] 검색어 = "+query, Toast.LENGTH_LONG).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //검색어 입력중
                //Toast.makeText(MainActivity.this, "입력하고있는 단어 = "+newText, Toast.LENGTH_LONG).show();
                return false;
            }
        });

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        /*switch (curId) {
            case R.id.menu_search:
                break;
            default:
                break;
        }*/
        return super.onOptionsItemSelected(item);
    }

}
