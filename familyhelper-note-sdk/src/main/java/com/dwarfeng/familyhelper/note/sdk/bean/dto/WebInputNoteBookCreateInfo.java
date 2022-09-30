package com.dwarfeng.familyhelper.note.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookCreateInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 笔记本创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputNoteBookCreateInfo implements Dto {

    private static final long serialVersionUID = 5081048004685316089L;

    public static NoteBookCreateInfo toStackBean(WebInputNoteBookCreateInfo webInputNoteBookCreateInfo) {
        if (Objects.isNull(webInputNoteBookCreateInfo)) {
            return null;
        } else {
            return new NoteBookCreateInfo(
                    webInputNoteBookCreateInfo.getName(), webInputNoteBookCreateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @JSONField(name = "remark")
    private String remark;

    public WebInputNoteBookCreateInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputNoteBookCreateInfo{" +
                "name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
