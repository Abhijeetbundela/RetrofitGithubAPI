package com.example.retrofitgithubapi.model;

import com.google.gson.annotations.SerializedName;

public class GitHubRepo {
    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private String language;

    @SerializedName("created_at")
    private String created;

    @SerializedName("updated_at")
    private String updated;

    @SerializedName("pushed_at")
    private String pushed;

    public GitHubRepo(String name, String description, String language, String created, String updated, String pushed) {
        this.name = name;
        this.description = description;
        this.language = language;
        this.created = created;
        this.updated = updated;
        this.pushed = pushed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getPushed() {
        return pushed;
    }

    public void setPushed(String pushed) {
        this.pushed = pushed;
    }
}
