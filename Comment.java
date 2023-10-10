public class Comment {
    private int commentId;
    private String commentText;
    private int reportId;
    private int commenterId;

    // Constructor
    public Comment(int commentId, String commentText, int reportId, int commenterId) {
        this.commentId = commentId;
        this.commentText = commentText;
        this.reportId = reportId;
        this.commenterId = commenterId;
    }

    // Getters
    public int getCommentId() {
        return commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public int getReportId() {
        return reportId;
    }

    public int getCommenterId() {
        return commenterId;
    }

    // Setters
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public void setCommenterId(int commenterId) {
        this.commenterId = commenterId;
    }

    // toString method
    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentText='" + commentText + '\'' +
                ", reportId=" + reportId +
                ", commenterId=" + commenterId +
                '}';
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (commentId != comment.commentId) return false;
        if (reportId != comment.reportId) return false;
        if (commenterId != comment.commenterId) return false;
        return commentText != null ? commentText.equals(comment.commentText) : comment.commentText == null;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
        result = 31 * result + reportId;
        result = 31 * result + commenterId;
        return result;
    }
}
