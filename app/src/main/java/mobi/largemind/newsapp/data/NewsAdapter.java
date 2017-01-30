package mobi.largemind.newsapp.data;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mobi.largemind.newsapp.R;

/**
 * Created by luciofm on 30/01/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    ArrayList<News> news = new ArrayList<>();

    public NewsAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.news_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindTo(news.get(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public void add(List<News> news) {
        this.news.addAll(news);
        notifyDataSetChanged();
    }

    public void clear() {
        news.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView section;
        TextView title;
        TextView author;
        TextView date;

        News news;

        public ViewHolder(View itemView) {
            super(itemView);
            section = (TextView) itemView.findViewById(R.id.section);
            title = (TextView) itemView.findViewById(R.id.title);
            author = (TextView) itemView.findViewById(R.id.author);
            date = (TextView) itemView.findViewById(R.id.date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.webUrl()));
                    v.getContext().startActivity(intent);
                }
            });
        }

        public void bindTo(News news) {
            this.news = news;
            section.setText(news.sectionName());
            title.setText(news.webTitle());
            author.setText(news.author());
            date.setText(news.webPublicationDate());
        }
    }
}
