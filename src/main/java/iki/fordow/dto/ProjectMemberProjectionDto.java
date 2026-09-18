package iki.fordow.dto;

import java.time.LocalDateTime;

public class ProjectMemberProjectionDto {

    private Long memberId;
    private Long projectId;
    private String memberName;
    private String projectName;
    private LocalDateTime joinedAt;

    public ProjectMemberProjectionDto(Long memberId, Long projectId, String memberName,
                                      String projectName, LocalDateTime joinedAt) {
        this.memberId = memberId;
        this.projectId = projectId;
        this.memberName = memberName;
        this.projectName = projectName;
        this.joinedAt = joinedAt;
    }

    public ProjectMemberProjectionDto(){

    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}
