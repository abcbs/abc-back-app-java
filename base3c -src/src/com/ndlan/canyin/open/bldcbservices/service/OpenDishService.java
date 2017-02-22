package com.ndlan.canyin.open.bldcbservices.service;

import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.ylgl.DishesRaw;
import com.ndlan.canyin.base.repository.ylgl.DishesRawDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly=true)
public class OpenDishService extends BaseService<DishesRawDao, DishesRaw>
{
}

