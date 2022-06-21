package com.kevin.room_library;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author : 王康
 * @date : 2022/6/21
 * @desc :
 */
@Entity
public class Group {

    @PrimaryKey(autoGenerate = true)
    public int gid;
    @ColumnInfo(name = "group_type")
    public String type;
    @ColumnInfo(name = "group_name")
    public String groupName;
    @ColumnInfo(name = "group_creation_time")
    public String creationTime;
    @ColumnInfo(name = "group_remark")
    public String remark;

    public Group setGid(int gid) {
        this.gid = gid;
        return this;
    }

    public Group setType(String type) {
        this.type = type;
        return this;
    }

    public Group setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public Group setCreationTime(String creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    public Group setRemark(String remark) {
        this.remark = remark;
        return this;
    }


    public int getGid() {
        return gid;
    }

    public String getType() {
        return type;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getRemark() {
        return remark;
    }

    @NonNull
    @Override
    public String toString() {
        return "Group{" +
                "gid=" + getGid() +
                ", type='" + getType() + '\'' +
                ", groupName='" + getGroupName() + '\'' +
                ", creationTime='" + getCreationTime() + '\'' +
                ", remark='" + getRemark() + '\'' +
                '}';
    }
}
