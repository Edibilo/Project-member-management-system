package iki.fordow.dto;

public class ProjectMemberDto {
    private Long id;
    private Long projectId;
    private Long memberId;

    public ProjectMemberDto(Long id, Long projectId, Long memberId) {
        this.id = id;
        this.projectId = projectId;
        this.memberId = memberId;
    }

    public ProjectMemberDto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
