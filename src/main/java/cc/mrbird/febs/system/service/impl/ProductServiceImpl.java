package cc.mrbird.febs.system.service.impl;


import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Product;
import cc.mrbird.febs.system.mapper.ProductMapper;
import cc.mrbird.febs.system.service.IProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private IProductService iProductService;

    @Override
    public List<Product> findProducts(Product product) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    //分页查找
    @Override
    public IPage<Product> findProducts(Product product, QueryRequest request) {
        Page<Product> page = new Page<>(request.getPageNum(), request.getPageSize());
        //SortUtil.handlePageSort(request, page, "product_id", FebsConstant.ORDER_DESC, false);
        return this.baseMapper.findProductPage(page, product);
    }

    //添加
    @Transactional
    public void createProduct(Product product) {
        this.baseMapper.insert(product);
//        this.save(product);
    }

    //删除
    @Override
    @Transactional
    public void deleteProduct(String roleIds) {
        List<String> list = Arrays.asList(roleIds.split(StringPool.COMMA));
        this.baseMapper.delete(new QueryWrapper<Product>().lambda().in(Product::getProductId, list));
    }
    //修改
    @Override
    @Transactional
    public void updateProduct(Product product) {
        this.updateById(product);
        List<String> productIdList = new ArrayList<>();
        productIdList.add(String.valueOf(product.getProductId()));
    }


}
