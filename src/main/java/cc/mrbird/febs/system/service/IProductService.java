package cc.mrbird.febs.system.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Product;
import cc.mrbird.febs.system.entity.Role;
import cc.mrbird.febs.system.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


    public interface IProductService extends IService<Product> {
    //查找所有产品
    List<Product> findProducts(Product product);
    //查找所有产品（分页）
    IPage<Product> findProducts(Product product, QueryRequest request);
    //添加产品
    void createProduct(Product product);
    //删除
    void deleteProduct(String roleIds);
    //修改
    void updateProduct(Product product);
}
