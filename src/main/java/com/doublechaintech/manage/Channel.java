package com.doublechaintech.manage;

import java.util.ArrayList;
import java.util.List;

public class Channel {

    List<Audience> audienceList;

    public List<Audience> getAudienceList() {
        if(audienceList==null){
            audienceList=new ArrayList<>();
        }
        return audienceList;
    }

    public void setAudienceList(List<Audience> audienceList) {
        this.audienceList = audienceList;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean addAudience(Audience audience) {
        long count=getAudienceList().stream().filter(a->a.getName().equals(audience.getName())).count();
        if(count>0){
            //alread
            return false;
        }
        getAudienceList().add(audience);
        return true;
    }
}
