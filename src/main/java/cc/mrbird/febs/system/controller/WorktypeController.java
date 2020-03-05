package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;

import cc.mrbird.febs.system.entity.Worktype;
import cc.mrbird.febs.system.service.IWorktypeService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 *  Controller
 *
 * @author MrBird
 * @date 2019-11-13 10:27:41
 */
@Slf4j
@Validated
@Controller
public class WorktypeController extends BaseController {

    @Autowired
    private IWorktypeService worktypeService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "worktype")
    public String worktypeIndex(){
        return FebsUtil.view("worktype/worktype");
    }

    //查询全部
    @GetMapping("worktype")
    @ResponseBody
    @RequiresPermissions("worktype:list")
    public FebsResponse getAllWorktypes(Worktype worktype) {
        return new FebsResponse().success().data(worktypeService.findWorktypes(worktype));
    }

    @GetMapping("worktype/list")
    @ResponseBody
    @RequiresPermissions("worktype:list")
    public FebsResponse worktypeList(QueryRequest request, Worktype worktype) {
        Map<String, Object> dataTable = getDataTable(this.worktypeService.findWorktypes(request, worktype));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Worktype", exceptionMessage = "新增Worktype失败")
    @PostMapping("worktype")
    @ResponseBody
    @RequiresPermissions("worktype:add")
    public FebsResponse addWorktype(@Valid Worktype worktype) {
        this.worktypeService.createWorktype(worktype);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除Worktype", exceptionMessage = "删除Worktype失败")
    @GetMapping("worktype/delete")
    @ResponseBody
    @RequiresPermissions("worktype:delete")
    public FebsResponse deleteWorktype(Worktype worktype) {
        this.worktypeService.deleteWorktype(worktype);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Worktype", exceptionMessage = "修改Worktype失败")
    @PostMapping("worktype/update")
    @ResponseBody
    @RequiresPermissions("worktype:update")
    public FebsResponse updateWorktype(Worktype worktype) {
        this.worktypeService.updateWorktype(worktype);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "导出Excel成功", exceptionMessage = "导出Excel失败")
    @PostMapping("worktype/excel")
    @ResponseBody
    @RequiresPermissions("worktype:export")
    public void export(QueryRequest queryRequest, Worktype worktype, HttpServletResponse response) {
        List<Worktype> worktypes = this.worktypeService.findWorktypes(queryRequest, worktype).getRecords();
        ExcelKit.$Export(Worktype.class, response).downXlsx(worktypes, false);
    }
}
