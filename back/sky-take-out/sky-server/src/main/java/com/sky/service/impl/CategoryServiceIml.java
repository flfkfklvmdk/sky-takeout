package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceIml implements CategoryService {

    /**
     * 新增菜品
     *
     * @param employeeDto
     */
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();

        //拷贝对象
        BeanUtils.copyProperties(categoryDTO, category);

//        //设置创建时间
//        category.setCreateTime(LocalDateTime.now());
//
//        //设置更新时间
//        category.setUpdateTime(LocalDateTime.now());
//
//        //设置创建人
//        category.setCreateUser(BaseContext.getCurrentId());
//
//        //设置修改人
//        category.setUpdateUser(BaseContext.getCurrentId());

        //设置状态
        category.setStatus(1);

        //保存数据
        categoryMapper.insert(category);
    }

    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        //设置分页信息
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());

        //查询数据
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);

        //封装数据
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void deleteById(Long id) {
        //根据id删除分类
        categoryMapper.deleteById(id);
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        //创建分类对象
        Category category = new Category();

        //拷贝对象
        BeanUtils.copyProperties(categoryDTO, category);

//        //设置更新时间
//        category.setUpdateTime(LocalDateTime.now());
//
//        //设置修改人
//        category.setUpdateUser(BaseContext.getCurrentId());

        //更新数据
        categoryMapper.update(category);
    }

    @Override
    public void StartOrStop(Integer status, Long id) {
        //创建分类对象
        Category category = new Category();

        //设置状态
        category.setStatus(status);

        //设置id
        category.setId(id);

        //更新数据
        categoryMapper.update(category);
    }

    @Override
    public List<Category> list(Integer type) {
        //查询数据
        List<Category> list = categoryMapper.list(type);

        //返回数据
        return list;
    }
}
