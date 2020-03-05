package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.system.entity.Product;
import cc.mrbird.febs.system.service.IProductService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("product")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public FebsResponse getAllProduct(Product product) {
        return new FebsResponse().success().data(productService.findProducts(product));
    }

    @GetMapping("list")
    @RequiresPermissions("product:view")
    public FebsResponse productList(Product product, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.productService.findProducts(product, request));
        System.out.println(dataTable);
        return new FebsResponse().success().data(dataTable);
    }

    @PostMapping
    @RequiresPermissions("product:add")
    @ControllerEndpoint(operation = "新增产品", exceptionMessage = "新增产品失败")
    public FebsResponse addProduct(@Valid Product product) {
        this.productService.createProduct(product);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{productIds}")
    @RequiresPermissions("product:delete")
    @ControllerEndpoint(operation = "删除角色", exceptionMessage = "删除角色失败")
    public FebsResponse deleteProduct(@NotBlank(message = "{required}") @PathVariable String productIds) {
        this.productService.deleteProduct(productIds);
        return new FebsResponse().success();
    }

    @PostMapping("update")
    @RequiresPermissions("product:update")
    @ControllerEndpoint(operation = "修改角色", exceptionMessage = "修改角色失败")
    public FebsResponse updateProduct(Product product) {
        this.productService.updateProduct(product);
        return new FebsResponse().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("product:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest queryRequest, Product product, HttpServletResponse response) throws FebsException {
        List<Product> products = this.productService.findProducts(product, queryRequest).getRecords();
        ExcelKit.$Export(Product.class, response).downXlsx(products, false);
    }


}
