package cc.mrbird.febs.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_product")
@Excel("产品息表")
public class Product implements Serializable {
    private static final long serialVersionUID = 8571011372410167901L;

    //产品id
    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId;
    //用户id（外键）
    @TableId(value = "USER_ID")
    private Long userId;
    //用户名
    @TableId(value = "USERNAME")
    private String userName;
    //产品名称
    @TableField("product_name")
    private String productName;
    //产品数量
    @TableField("product_quantity")
    private String productQuantity;
    //产品价格
    @TableField("product_price")
    private String productPrice;
    //产品图号
    @TableField("drawing_number")
    private String drawingNumber;

}
