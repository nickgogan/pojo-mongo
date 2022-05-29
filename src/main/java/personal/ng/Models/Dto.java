package personal.ng.Models;

import java.util.*;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import personal.ng.JacksonHelpers.StringDeserializer;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
        "body",
        "permalink",
        "author",
        "title",
        "tags",
        "comments",
        "date"
})
@Generated("jsonschema2pojo")
public class Dto {
    @JsonProperty("body")
    @JsonDeserialize(using = StringDeserializer.class)
    @BsonProperty("body")
    public String body;
    @JsonProperty("permalink")
    @JsonDeserialize(using = StringDeserializer.class)
    @BsonProperty("permalink")
    public String permalink;
    @JsonProperty("author")
    @JsonDeserialize(using = StringDeserializer.class)
    @BsonProperty("author")
    public String author;
    @JsonProperty("title")
    @JsonDeserialize(using = StringDeserializer.class)
    @BsonProperty("title")
    public String title;
    @JsonProperty("tags")
    @BsonProperty("tags")
    public List<String> tags = new ArrayList<String>();
    @JsonProperty("comments")
    @BsonProperty("comments")
    public List<Comment> comments = new ArrayList<Comment>();
    @JsonProperty("date")
    @BsonProperty("date")
//    @BsonRepresentation(BsonType.DATE_TIME)
    public Date date;
    @JsonIgnore
    @BsonIgnore
    public Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Dto() {
    }

    /**
     *
     * @param date
     * @param comments
     * @param author
     * @param body
     * @param permalink
     * @param title
     * @param tags
     */
    public Dto(String body, String permalink, String author, String title, List<String> tags, List<Comment> comments, Date date) {
        super();
        this.body = body;
        this.permalink = permalink;
        this.author = author;
        this.title = title;
        this.tags = tags;
        this.comments = comments;
        this.date = date;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("permalink")
    public String getPermalink() {
        return permalink;
    }

    @JsonProperty("permalink")
    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty("comments")
    public List<Comment> getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @JsonProperty("date")
    public Date getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(Date date) {
        this.date = date;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Dto.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("body");
        sb.append('=');
        sb.append(((this.body == null)?"<null>":this.body));
        sb.append(',');
        sb.append("permalink");
        sb.append('=');
        sb.append(((this.permalink == null)?"<null>":this.permalink));
        sb.append(',');
        sb.append("author");
        sb.append('=');
        sb.append(((this.author == null)?"<null>":this.author));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("tags");
        sb.append('=');
        sb.append(((this.tags == null)?"<null>":this.tags));
        sb.append(',');
        sb.append("comments");
        sb.append('=');
        sb.append(((this.comments == null)?"<null>":this.comments));
        sb.append(',');
        sb.append("date");
        sb.append('=');
        sb.append(((this.date == null)?"<null>":this.date));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}