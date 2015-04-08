package com.adserver.datatype;

/**
 * Input for the client containing ad details which client requested.
 */
public class AdMedia {

    private String title;
    private String url;
    private byte[] image;

    public AdMedia() {}

    public AdMedia(final String title, final String url, final byte[] image) {
        this.title = title;
        this.url = url;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(final byte[] image) {
        this.image = image;
    }
}
