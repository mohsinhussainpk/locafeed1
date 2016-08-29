package com.example.mohsinhussain.locafeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohsinhussain on 28/08/2016.
 */
public class CommentsAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public CommentsAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Comments object) {
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
        // PostsHolder postHolder;
        CommentsHolder commentHolder;
        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.comment_row,parent,false);
            commentHolder = new CommentsHolder();
//yahan masla ho sakta hai
            commentHolder.tx_comment = (TextView)row.findViewById(R.id.tx_comment);
            // commentHolder.comment = (TextView)row.findViewById(R.id.tx_comment);
            //  postHolder.tx_title = (TextView)row.findViewById(R.id.tx_title);
            //  postHolder.tx_votes = (TextView)row.findViewById(R.id.tx_votes);
            //  postHolder.tx_userid = (TextView)row.findViewById(R.id.tx_userid);


            row.setTag(commentHolder);
        }

        else
        {
            commentHolder = (CommentsHolder) row.getTag();


        }

        Comments comments = (Comments) this.getItem(position);
        // postHolder.tx_title.setText(posts.getTitle());
        commentHolder.tx_comment.setText(comments.getComments());
     /*   postHolder.tx_votes.setText(posts.getVotes());
        postHolder.tx_description.setText(posts.getDescription());
        postHolder.tx_userid.setText(posts.getUserId());
*/
        return row;
    }

    static class CommentsHolder{

        TextView tx_comment;


    }
}
