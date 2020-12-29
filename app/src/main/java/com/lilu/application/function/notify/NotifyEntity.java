package com.lilu.application.function.notify;

/**
 * Description:
 *
 * @author lilu on 2020/12/25
 * No one knows this better than me
 */
public class NotifyEntity {

    private int id;
    private String title;
    private String content;

    public NotifyEntity(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
