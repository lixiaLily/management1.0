package cc.mrbird.febs.system.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author MrBird
 * @date 2019-11-13 10:27:41
 */
@Data
@TableName("t_worktype")
public class Worktype {

    /**
     *主键
     */
    @TableId(value = "worktype_id", type = IdType.AUTO)
    private Integer worktypeId;
    /**
     * 名称
     */
    @TableField("worktype_name")
    private String worktypeName;



}
