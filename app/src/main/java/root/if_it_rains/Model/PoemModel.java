package root.if_it_rains.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by root1 on 2017. 9. 1..
 */

@SuppressWarnings("serial")
public class PoemModel implements Serializable{
    @SerializedName("id")
    private String id;
    @SerializedName("writer")
    private String writer;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
