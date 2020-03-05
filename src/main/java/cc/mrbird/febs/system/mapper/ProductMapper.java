package cc.mrbird.febs.system.mapper;

import cc.mrbird.febs.system.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper extends BaseMapper<Product> {
    //查找所有产品
    List<Product> findAllProduct(Product product);
    //分页查找所有产品
    IPage<Product> findProductPage(Page page,@Param("product")Product product);
}
