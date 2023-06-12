package com.example.servergames.Model.POJO;

import java.util.List;

public class CRUDUser {
    private String documentId;
    private String name;
    private String profession;
    private List<Integer> recordIds;

    public List<Integer> getRecordIds() {
        return recordIds;
    }

    public void setRecordIds(List<Integer> recordIds) {
        this.recordIds = recordIds;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
