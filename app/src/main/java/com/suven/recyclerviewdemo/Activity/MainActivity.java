package com.suven.recyclerviewdemo.Activity;

import android.content.res.Configuration;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.suven.recyclerviewdemo.Adapter.UserAdapter;
import com.suven.recyclerviewdemo.R;
import com.suven.recyclerviewdemo.Model.UserDetails;

import java.util.ArrayList;
//created by sid in subcat 123
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;
    private TextInputLayout tilSearch;
    ArrayList<UserDetails> lstUsers = new ArrayList<>();
    private TextView tvNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Hello Everyone");
        setSupportActionBar(toolbar);

        tvNoData = (TextView) findViewById(R.id.tvNoData);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        tilSearch = (TextInputLayout) findViewById(R.id.tilSearch);
        lstUsers = bindUserData();

        final Handler handler = new Handler();
        tilSearch.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                try {
                    lstUsers = bindUserData();
                    ArrayList<UserDetails> lstUsersRemoved = new ArrayList<UserDetails>();
                    if (!charSequence.toString().isEmpty()) {
                        for (UserDetails userDetails : lstUsers) {
                            if (userDetails.getName().toLowerCase().contains(charSequence.toString().toLowerCase())
                                    || String.valueOf(userDetails.getAge()).contains(charSequence.toString().toLowerCase())
                                    || userDetails.getMobileNo().contains(charSequence.toString().toLowerCase())) {
                                lstUsersRemoved.add(userDetails);
                            }
                        }
                        lstUsers.clear();
                        lstUsers.addAll(lstUsersRemoved);
                    }


                    handler.removeCallbacksAndMessages(null);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.setData(lstUsers);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 600);


                    showNoData(lstUsers.isEmpty());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        bindRecyclerview(new LinearLayoutManager(getApplicationContext()), R.layout.user_list_row);
    }

    private void bindRecyclerview(RecyclerView.LayoutManager mLayoutManager, int resId) {
        mAdapter = new UserAdapter(this, resId);
        mAdapter.setData(lstUsers);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private ArrayList<UserDetails> bindUserData() {
        ArrayList<UserDetails> lstUser = new ArrayList<>();
        UserDetails userDetails = new UserDetails();
        userDetails.setName("Siddhesh Prakash Gawde");
        userDetails.setAge(25);
        userDetails.setImageUrl("https://scontent.fbom9-1.fna.fbcdn.net/v/t1.0-1/p160x160/14520526_1098399723529547_4585856403665112824_n.jpg?oh=aab19199ba94a33dc3c347e0d27f132c&oe=5A489176");
        userDetails.setMobileNo("9773523263");
        userDetails.setPremiumUser(true);
        lstUser.add(userDetails);


        userDetails = new UserDetails();
        userDetails.setName("Rocky Jagtiani");
        userDetails.setAge(40);
        userDetails.setImageUrl("http://suvenconsultants.com/mainpagefiles/images/rockysir.png");
        userDetails.setMobileNo("456645213155");
        userDetails.setPremiumUser(false);
        lstUser.add(userDetails);


        userDetails = new UserDetails();
        userDetails.setName("Rocky Jagtiani");
        userDetails.setAge(35);
        userDetails.setImageUrl("http://suvenconsultants.com/mainpagefiles/images/sim1.jpg");
        userDetails.setMobileNo("456645213155");
        userDetails.setPremiumUser(false);
        lstUser.add(userDetails);


        userDetails = new UserDetails();
        userDetails.setName("Rakesh Prashant Desai");
        userDetails.setAge(28);
        userDetails.setImageUrl("https://firebasestorage.googleapis.com/v0/b/testapp-967f5.appspot.com/o/1.jpg?alt=media&token=d262c165-7ecc-4d48-87ee-20b98e21e985");
        userDetails.setMobileNo("9778565236");
        userDetails.setPremiumUser(false);
        lstUser.add(userDetails);


        userDetails = new UserDetails();
        userDetails.setName("Ghanshyam gupta");
        userDetails.setAge(31);
        userDetails.setImageUrl("https://firebasestorage.googleapis.com/v0/b/testapp-967f5.appspot.com/o/2.jpg?alt=media&token=fa29f4d1-8c73-424e-ae4d-f831c4f34dd1");
        userDetails.setMobileNo("97503326533");
        userDetails.setPremiumUser(false);
        lstUser.add(userDetails);


        userDetails = new UserDetails();
        userDetails.setName("Smita rane");
        userDetails.setAge(25);
        userDetails.setImageUrl("https://firebasestorage.googleapis.com/v0/b/testapp-967f5.appspot.com/o/10.jpg?alt=media&token=fae8a3bb-689e-44cc-b23c-588a20b16142");
        userDetails.setMobileNo("895652359");
        userDetails.setPremiumUser(true);
        lstUser.add(userDetails);

        userDetails = new UserDetails();
        userDetails.setName("Sangeeta Desai");
        userDetails.setAge(31);
        userDetails.setImageUrl("");
        userDetails.setMobileNo("9773526569");
        userDetails.setPremiumUser(false);
        lstUser.add(userDetails);


        userDetails = new UserDetails();
        userDetails.setName("Arohi shinde");
        userDetails.setAge(25);
        userDetails.setImageUrl("https://firebasestorage.googleapis.com/v0/b/testapp-967f5.appspot.com/o/9.jpg?alt=media&token=eae7a770-e6ff-4bc1-80fb-5a4cb35f04c1");
        userDetails.setMobileNo("78956252325");
        userDetails.setPremiumUser(false);
        lstUser.add(userDetails);

        userDetails = new UserDetails();
        userDetails.setName("Siddhesh Chapekar");
        userDetails.setAge(25);
        userDetails.setImageUrl("https://firebasestorage.googleapis.com/v0/b/testapp-967f5.appspot.com/o/3.jpg?alt=media&token=73181c4c-75ef-4317-9afd-beeb0a6071fd");
        userDetails.setMobileNo("789564542366");
        userDetails.setPremiumUser(true);
        lstUser.add(userDetails);

        userDetails = new UserDetails();
        userDetails.setName("Pranita Chavan");
        userDetails.setAge(21);
        userDetails.setImageUrl("");
        userDetails.setMobileNo("9778523569");
        userDetails.setPremiumUser(false);
        lstUser.add(userDetails);


        userDetails = new UserDetails();
        userDetails.setName("Prakash Gawde");
        userDetails.setAge(28);
        userDetails.setImageUrl("https://firebasestorage.googleapis.com/v0/b/testapp-967f5.appspot.com/o/4.jpg?alt=media&token=9113359e-763d-4f49-a8b7-4a3050bdc918");
        userDetails.setMobileNo("1245863566");
        userDetails.setPremiumUser(true);
        lstUser.add(userDetails);


        userDetails = new UserDetails();
        userDetails.setName("Pooja Gawde");
        userDetails.setAge(25);
        userDetails.setImageUrl("");
        userDetails.setMobileNo("89566223314");
        userDetails.setPremiumUser(false);
        lstUser.add(userDetails);


        userDetails = new UserDetails();
        userDetails.setName("Rushabh Patel");
        userDetails.setAge(25);
        userDetails.setImageUrl("https://firebasestorage.googleapis.com/v0/b/testapp-967f5.appspot.com/o/2.jpg?alt=media&token=fa29f4d1-8c73-424e-ae4d-f831c4f34dd1");
        userDetails.setMobileNo("456645213155");
        userDetails.setPremiumUser(true);
        lstUser.add(userDetails);


        userDetails = new UserDetails();
        userDetails.setName("Gaurav shiroadkar");
        userDetails.setAge(25);
        userDetails.setImageUrl("");
        userDetails.setMobileNo("1111111111");
        userDetails.setPremiumUser(false);
        lstUser.add(userDetails);


        userDetails = new UserDetails();
        userDetails.setName("himu Padhye");
        userDetails.setAge(25);
        userDetails.setImageUrl("https://firebasestorage.googleapis.com/v0/b/testapp-967f5.appspot.com/o/1.jpg?alt=media&token=d262c165-7ecc-4d48-87ee-20b98e21e985");
        userDetails.setMobileNo("4541213465498");
        userDetails.setPremiumUser(false);
        lstUser.add(userDetails);


        userDetails = new UserDetails();
        userDetails.setName("Pranit Hodvadekr");
        userDetails.setAge(25);
        userDetails.setImageUrl("");
        userDetails.setMobileNo("2458774666223");
        userDetails.setPremiumUser(false);
        lstUser.add(userDetails);


        userDetails = new UserDetails();
        userDetails.setName("Solomon Rathod");
        userDetails.setAge(25);
        userDetails.setImageUrl("https://firebasestorage.googleapis.com/v0/b/testapp-967f5.appspot.com/o/9.jpg?alt=media&token=eae7a770-e6ff-4bc1-80fb-5a4cb35f04c1");
        userDetails.setMobileNo("14545548");
        userDetails.setPremiumUser(false);
        lstUser.add(userDetails);


        userDetails = new UserDetails();
        userDetails.setName("Ashish Rane");
        userDetails.setAge(25);
        userDetails.setImageUrl("");
        userDetails.setMobileNo("1212336565665");
        userDetails.setPremiumUser(false);
        lstUser.add(userDetails);


        userDetails = new UserDetails();
        userDetails.setName("Santoor Rawool");
        userDetails.setAge(25);
        userDetails.setImageUrl("");
        userDetails.setMobileNo("123246546");
        userDetails.setPremiumUser(false);
        lstUser.add(userDetails);


        String jsonData = new Gson().toJson(lstUser);


        return lstUser;
    }


//    private ArrayList<UserDetails> bindUserDataFromJsonParsing() {
//        ArrayList<UserDetails> lstUser = new ArrayList<>();
//        try {
//            JSONArray jsonAarray = new JSONArray(Util.jsonData);
//
//            for (int i = 0; i < jsonAarray.length(); i++) {
//
//                JSONObject jsonObject = jsonAarray.getJSONObject(i);
//                int age = jsonObject.getInt("age");
//                String imageUrl = jsonObject.getString("imageUrl");
//                boolean isPremium = jsonObject.getBoolean("isPremiumUser");
//                String mobile = jsonObject.getString("mobileNo");
//                String name = jsonObject.getString("name");
//
//                UserDetails userDetails = new UserDetails();
//
//                userDetails.setAge(age);
//                userDetails.setImageUrl(imageUrl);
//                userDetails.setPremiumUser(isPremium);
//                userDetails.setMobileNo(mobile);
//                userDetails.setName(name);
//                lstUser.add(userDetails);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        return lstUser;
//    }

//    private ArrayList<UserDetails> bindUserDataFromGSON() {
//        ArrayList<UserDetails> lstUser = new ArrayList<>();
//        try {
//            Gson gson = new Gson();
//            Type type = new TypeToken<ArrayList<UserDetails>>() {
//            }.getType();
//            lstUser = gson.fromJson(Util.jsonData, type);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return lstUser;
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.actionChangeView) {
            RecyclerView.LayoutManager mLayoutManager = null;
            int resID = 0;
            if (item.getIcon().getConstantState().equals(ContextCompat.getDrawable(this, R.drawable.ic_rertical).getConstantState())) {
                mLayoutManager = new LinearLayoutManager(getApplicationContext());
                resID = R.layout.user_list_row;
                item.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_grid));
            } else {
                mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                resID = R.layout.user_list_card_row;
                item.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_rertical));
            }
            bindRecyclerview(mLayoutManager, resID);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        RecyclerView.LayoutManager mLayoutManager = null;
        int resID = 0;
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            resID = R.layout.user_list_card_row;
            Toast.makeText(this, "landscape mode", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mLayoutManager = new LinearLayoutManager(getApplicationContext());
            resID = R.layout.user_list_row;
            Toast.makeText(this, "portrait mode", Toast.LENGTH_SHORT).show();
        }

        bindRecyclerview(mLayoutManager, resID);
    }

    private void showNoData(boolean isEmpty) {
        tvNoData.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
    }
}
