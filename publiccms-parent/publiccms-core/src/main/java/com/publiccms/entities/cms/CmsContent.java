package com.publiccms.entities.cms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.ClassBridge;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.FacetEncodingType;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.SortableField;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.bridge.builtin.IntegerBridge;
import org.hibernate.search.bridge.builtin.ShortBridge;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.publiccms.common.database.CmsUpgrader;
import com.publiccms.common.generator.annotation.GeneratorColumn;
import com.publiccms.common.search.CmsContentBridge;
import com.publiccms.common.search.CmsContentInterceptor;
import com.publiccms.common.search.MultiTokenFilterFactory;
import com.publiccms.common.search.MultiTokenizerFactory;

/**
 * CmsContent generated by hbm2java
 */
@Entity
@Table(name = "cms_content")
@DynamicUpdate
@AnalyzerDef(name = "cms", tokenizer = @TokenizerDef(factory = MultiTokenizerFactory.class), filters = {
        @TokenFilterDef(factory = MultiTokenFilterFactory.class) })
@Analyzer(definition = "cms") // Comment this line to enable elasticsearch
// @Analyzer(definition = "default") // Uncomment this line to enable elasticsearch
@ClassBridge(impl = CmsContentBridge.class)
@Indexed(interceptor = CmsContentInterceptor.class)
public class CmsContent implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @GeneratorColumn(title = "ID")
    private Long id;
    @GeneratorColumn(title = "站点", condition = true)
    @FieldBridge(impl = ShortBridge.class)
    @Field(analyze = Analyze.NO)
    @JsonIgnore
    private short siteId;
    @GeneratorColumn(title = "标题", condition = true, like = true, or = true)
    @Field(store = Store.COMPRESS)
    private String title;
    @GeneratorColumn(title = "发布用户", condition = true)
    private long userId;
    @GeneratorColumn(title = "审核用户", condition = true)
    private Long checkUserId;
    @GeneratorColumn(title = "分类", condition = true)
    @Field(analyze = Analyze.NO, store = Store.YES)
    @Facet(encoding = FacetEncodingType.STRING)
    @FieldBridge(impl = IntegerBridge.class)
    private int categoryId;
    @GeneratorColumn(title = "模型", condition = true)
    @Field(analyze = Analyze.NO, store = Store.YES)
    @Facet(encoding = FacetEncodingType.STRING)
    private String modelId;
    @GeneratorColumn(title = "父内容", condition = true)
    @Field(analyze = Analyze.NO, store = Store.YES)
    private Long parentId;
    @GeneratorColumn(title = "引用内容ID", condition = true)
    @Field(analyze = Analyze.NO, store = Store.YES)
    private Long quoteContentId;
    @GeneratorColumn(title = "是否转载")
    private boolean copied;
    @GeneratorColumn(title = "是否投稿")
    private boolean contribute;
    @GeneratorColumn(title = "作者")
    @Field(analyze = Analyze.NO, store = Store.YES)
    private String author;
    @GeneratorColumn(title = "编辑")
    @Field(analyze = Analyze.NO, store = Store.YES)
    private String editor;
    @GeneratorColumn(title = "外链")
    @Field(analyze = Analyze.NO, store = Store.YES)
    private boolean onlyUrl;
    @GeneratorColumn(title = "有图片列表", condition = true)
    @Field(analyze = Analyze.NO, store = Store.YES)
    private boolean hasImages;
    @GeneratorColumn(title = "有附件列表", condition = true)
    @Field(analyze = Analyze.NO, store = Store.YES)
    private boolean hasFiles;
    @GeneratorColumn(title = "有静态化")
    private boolean hasStatic;
    @GeneratorColumn(title = "地址")
    @Field(analyze = Analyze.NO, store = Store.YES)
    private String url;
    @GeneratorColumn(title = "描述")
    @Field(store = Store.COMPRESS)
    private String description;
    @GeneratorColumn(title = "标签")
    @Field(store = Store.YES)
    private String tagIds;
    @GeneratorColumn(title = "数据字典值")
    @Field(store = Store.YES)
    private String dictionaryValues;
    @GeneratorColumn(title = "封面")
    private String cover;
    @GeneratorColumn(title = "子内容数")
    private int childs;
    @GeneratorColumn(title = "分数", order = true)
    private int scores;
    @GeneratorColumn(title = "评论数", order = true)
    private int comments;
    @GeneratorColumn(title = "点击数", order = true)
    private int clicks;
    @GeneratorColumn(title = "发布日期", condition = true, order = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Field(analyze = Analyze.NO, store = Store.YES)
    @DateBridge(resolution = Resolution.SECOND)
    @SortableField
    private Date publishDate;
    @GeneratorColumn(title = "过期日期", condition = true, order = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Field(analyze = Analyze.NO, store = Store.YES, indexNullAs = "0")
    @DateBridge(resolution = Resolution.SECOND)
    private Date expiryDate;
    @GeneratorColumn(title = "审核日期", order = true)
    private Date checkDate;
    @GeneratorColumn(title = "更新日期", order = true)
    private Date updateDate;
    @GeneratorColumn(title = "创建日期")
    private Date createDate;
    @GeneratorColumn(title = "排序")
    private int sort;
    @GeneratorColumn(title = "状态", condition = true)
    private int status;
    @GeneratorColumn(title = "已删除", condition = true)
    @JsonIgnore
    private boolean disabled;

    public CmsContent() {
    }

    public CmsContent(short siteId, String title, long userId, int categoryId, String modelId, boolean copied, boolean onlyUrl,
            boolean hasImages, boolean hasFiles, boolean hasStatic, int childs, int scores, int comments, int clicks,
            Date publishDate, Date createDate, int sort, int status, boolean disabled) {
        this.siteId = siteId;
        this.title = title;
        this.userId = userId;
        this.categoryId = categoryId;
        this.modelId = modelId;
        this.copied = copied;
        this.onlyUrl = onlyUrl;
        this.hasImages = hasImages;
        this.hasFiles = hasFiles;
        this.hasStatic = hasStatic;
        this.childs = childs;
        this.scores = scores;
        this.comments = comments;
        this.clicks = clicks;
        this.publishDate = publishDate;
        this.createDate = createDate;
        this.sort = sort;
        this.status = status;
        this.disabled = disabled;
    }

    public CmsContent(short siteId, String title, long userId, Long checkUserId, int categoryId, String modelId, Long parentId,
            Long quoteContentId, boolean contribute, boolean copied, String author, String editor, boolean onlyUrl, boolean hasImages,
            boolean hasFiles, boolean hasStatic, String url, String description, String tagIds, String dictionaryValues,
            String cover, int childs, int scores, int comments, int clicks, Date publishDate, Date expiryDate, Date checkDate,
            Date updateDate, Date createDate, int sort, int status, boolean disabled) {
        this.siteId = siteId;
        this.title = title;
        this.userId = userId;
        this.checkUserId = checkUserId;
        this.categoryId = categoryId;
        this.modelId = modelId;
        this.parentId = parentId;
        this.quoteContentId = quoteContentId;
       this.contribute = contribute;
        this.copied = copied;
        this.author = author;
        this.editor = editor;
        this.onlyUrl = onlyUrl;
        this.hasImages = hasImages;
        this.hasFiles = hasFiles;
        this.hasStatic = hasStatic;
        this.url = url;
        this.description = description;
        this.tagIds = tagIds;
        this.dictionaryValues = dictionaryValues;
        this.cover = cover;
        this.childs = childs;
        this.scores = scores;
        this.comments = comments;
        this.clicks = clicks;
        this.publishDate = publishDate;
        this.expiryDate = expiryDate;
        this.checkDate = checkDate;
        this.updateDate = updateDate;
        this.createDate = createDate;
        this.sort = sort;
        this.status = status;
        this.disabled = disabled;
    }

    @Id
    @GeneratedValue(generator = "cmsGenerator")
    @GenericGenerator(name = "cmsGenerator", strategy = CmsUpgrader.IDENTIFIER_GENERATOR)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "site_id", nullable = false)
    public short getSiteId() {
        return this.siteId;
    }

    public void setSiteId(short siteId) {
        this.siteId = siteId;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "check_user_id")
    public Long getCheckUserId() {
        return this.checkUserId;
    }

    public void setCheckUserId(Long checkUserId) {
        this.checkUserId = checkUserId;
    }

    @Column(name = "category_id", nullable = false)
    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "model_id", nullable = false, length = 20)
    public String getModelId() {
        return this.modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    @Column(name = "parent_id")
    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Column(name = "quote_content_id")
    public Long getQuoteContentId() {
        return this.quoteContentId;
    }

    public void setQuoteContentId(Long quoteContentId) {
        this.quoteContentId = quoteContentId;
    }
    
	@Column(name = "contribute", nullable = false)
    public boolean isContribute() {
        return this.contribute;
    }
    
    public void setContribute(boolean contribute) {
        this.contribute = contribute;
    }

    @Column(name = "copied", nullable = false)
    public boolean isCopied() {
        return this.copied;
    }

    public void setCopied(boolean copied) {
        this.copied = copied;
    }
    
    
    @Column(name = "author", length = 50)
    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "editor", length = 50)
    public String getEditor() {
        return this.editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    @Column(name = "only_url", nullable = false)
    public boolean isOnlyUrl() {
        return this.onlyUrl;
    }

    public void setOnlyUrl(boolean onlyUrl) {
        this.onlyUrl = onlyUrl;
    }

    @Column(name = "has_images", nullable = false)
    public boolean isHasImages() {
        return this.hasImages;
    }

    public void setHasImages(boolean hasImages) {
        this.hasImages = hasImages;
    }

    @Column(name = "has_files", nullable = false)
    public boolean isHasFiles() {
        return this.hasFiles;
    }

    public void setHasFiles(boolean hasFiles) {
        this.hasFiles = hasFiles;
    }

    @Column(name = "has_static", nullable = false)
    public boolean isHasStatic() {
        return this.hasStatic;
    }

    public void setHasStatic(boolean hasStatic) {
        this.hasStatic = hasStatic;
    }

    @Column(name = "url", length = 1000)
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "description", length = 300)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "tag_ids", length = 65535)
    public String getTagIds() {
        return this.tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    @Column(name = "dictionary_values", length = 65535)
    public String getDictionaryValues() {
        return this.dictionaryValues;
    }

    public void setDictionaryValues(String dictionaryValues) {
        this.dictionaryValues = dictionaryValues;
    }

    @Column(name = "cover")
    public String getCover() {
        return this.cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Column(name = "childs", nullable = false)
    public int getChilds() {
        return this.childs;
    }

    public void setChilds(int childs) {
        this.childs = childs;
    }

    @Column(name = "scores", nullable = false)
    public int getScores() {
        return this.scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    @Column(name = "comments", nullable = false)
    public int getComments() {
        return this.comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    @Column(name = "clicks", nullable = false)
    public int getClicks() {
        return this.clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish_date", nullable = false, length = 19)
    public Date getPublishDate() {
        return this.publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiry_date", length = 19)
    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "check_date", length = 19)
    public Date getCheckDate() {
        return this.checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", length = 19)
    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, length = 19)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "sort", nullable = false)
    public int getSort() {
        return this.sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Column(name = "status", nullable = false)
    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name = "disabled", nullable = false)
    public boolean isDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

}