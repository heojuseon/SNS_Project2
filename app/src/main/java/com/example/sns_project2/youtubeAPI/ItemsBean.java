package com.example.sns_project2.youtubeAPI;

import com.google.gson.annotations.SerializedName;

public class ItemsBean {
    @SerializedName("id")
    private Id id;

    @SerializedName("snippet")
    private Snippet snippet;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }
    private class Id {
    }

    public static class Snippet {
        @SerializedName("title")
        private String title;

        @SerializedName("thumbnails")
        private Thumbnail thumbnails;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Thumbnail getThumbnails() {
            return thumbnails;
        }

        public void setThumbnails(Thumbnail thumbnails) {
            this.thumbnails = thumbnails;
        }

        public static class Thumbnail {
            @SerializedName("medium")
            private Medium medium;

            public Medium getMedium() {
                return medium;
            }

            public void setMedium(Medium medium) {
                this.medium = medium;
            }

            public static class Medium {
                @SerializedName("url")
                private String url;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
