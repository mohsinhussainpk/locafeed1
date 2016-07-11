package com.example.mohsinhussain.locafeed;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohsinhussain on 11/07/2016.
 */
public class PostsAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public PostsAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Posts object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        PostsHolder postHolder;
        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.custom_row,parent,false);
            postHolder = new PostsHolder();
//yahan masla ho sakta hai
            postHolder.tx_description = (TextView)row.findViewById(R.id.tx_description);
            postHolder.tx_title = (TextView)row.findViewById(R.id.tx_title);
            postHolder.tx_votes = (TextView)row.findViewById(R.id.tx_votes);
            row.setTag(postHolder);
        }

        else
        {
            postHolder = (PostsHolder) row.getTag();


        }

        Posts posts = (Posts) this.getItem(position);
        postHolder.tx_title.setText(posts.getTitle());
        postHolder.tx_votes.setText(posts.getVotes());
        postHolder.tx_description.setText(posts.getDescription());
        return row;
    }

    static class PostsHolder{

        TextView tx_title, tx_description, tx_votes;


    }
}
