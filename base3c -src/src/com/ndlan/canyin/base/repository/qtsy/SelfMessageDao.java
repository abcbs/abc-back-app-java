package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.qtsy.SelfMessage;
import com.ndlan.canyin.base.entity.qtsy.SelfOrder;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract interface SelfMessageDao extends BaseDao<SelfMessage, String>
{
  public abstract List<SelfMessage> findByRestId(String paramString);

  public abstract List<SelfMessage> findByMesTypeAndRestIdAndTabNo(String paramString1, String paramString2, String paramString3);

  public abstract List<SelfMessage> findByRestIdAndTabNo(String paramString1, String paramString2);

  public abstract List<SelfMessage> findByRestIdAndMesType(String paramString1, String paramString2);

  public abstract List<SelfMessage> findBySelfOrderAndMesTypeOrderByCreateTimeDesc(SelfOrder paramSelfOrder, String paramString);

  public abstract List<SelfMessage> findByRestIdAndStatus(String paramString1, String paramString2);

  public abstract List<SelfMessage> findByRestIdAndStatusOrderByUpdateTimeDesc(String paramString1, String paramString2);

  public abstract Page<SelfMessage> findByRestIdAndCreateTimeGreaterThanAndCreateTimeLessThanAndMesType(String paramString1, Date paramDate1, Date paramDate2, String paramString2, Pageable paramPageable);

  public abstract Page<SelfMessage> findByRestIdAndStatusAndUpdateTimeGreaterThanAndUpdateTimeLessThanAndMesTypeIn(String paramString1, String paramString2, Date paramDate1, Date paramDate2, Collection<String> paramCollection, Pageable paramPageable);
}

