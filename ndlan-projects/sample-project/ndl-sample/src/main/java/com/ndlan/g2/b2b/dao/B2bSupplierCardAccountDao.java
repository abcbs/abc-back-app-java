package com.ndlan.g2.b2b.dao;

import com.ndlan.g2.b2b.model.B2bSupplierCardAccountBean;

import java.util.List;
import org.springframework.jdbc.core.PreparedStatementSetter;
import com.ndlan.canyin.base.repository.BaseDao;
public interface B2bSupplierCardAccountDao  extends BaseDao<B2bSupplierCardAccountBean, String>{
    /**
     * ����������ѯ
     * @param id ����id
     * @return ��ѯ�����޽��ʱ����null
     */
    public B2bSupplierCardAccountBean selectByPrimaryKey(String supCardAcntId);

    /**
     * ����sql��ѯ
     * @param whereSql where�ؼ��ֺ���Ĳ�ѯ���������磺id = ?
     * @param params ��ѯ�����еĲ�����������Ҫ��"?"��������һ��
     * @return ��ѯ������޽��ʱ���ؿռ�¼��
     */
    public List<B2bSupplierCardAccountBean> selectByWhereSql(String whereSql, Object [] params);

    /**
     * ����sql��ҳ��ѯ, startPos��num����һ����
     * @param whereSql where�ؼ��ֺ���Ĳ�ѯ���������磺id = ?
     * @param params ��ѯ�����еĲ�����������Ҫ��"?"��������һ��
     * @param startPos ��ѯ��ʼλ�ã���0��ʼ
     * @param num ��ѯ��¼��
     * @return ��ѯ������޽��ʱ���ؿռ�¼��
     */
    public List<B2bSupplierCardAccountBean> selectByWhereSql(String whereSql, Object[] params, Long startPos, Long num);

    /**
     * ��ѯ���м�¼
     * @return ���м�¼���޽��ʱ���ؿռ�¼��
     */
    public List<B2bSupplierCardAccountBean> selectAll();

    /**
     * ͳ�Ƽ�¼��
     * @param whereSql where�ؼ��ֺ���Ĳ�ѯ���������磺id = ?
     * @param params ��ѯ�����еĲ�����������Ҫ��"?"��������һ��
     * @return ��¼��
     */
    public long count(String whereSql, Object[] params);

    /**
     * ��ѡ��Ĳ����¼��ֻ��������з�nullֵ���ֶ�
     * @param b2bSupplierCardAccount ���ݶ���
     * @return �ɹ�����ļ�¼��
     */
    public int insertSelective(B2bSupplierCardAccountBean b2bSupplierCardAccount);

    /**
    * ��ѡ��Ĳ����¼��ֻ��������з�nullֵ���ֶΣ�����ȡ����id��b2bSupplierCardAccount������
    * @param b2bSupplierCardAccount ���ݶ���
    * @return �ɹ�����ļ�¼��
    */
    public int insertSelectiveAndGetId(B2bSupplierCardAccountBean b2bSupplierCardAccount);

    /**
     * ��ѡ��ĸ��¼�¼��ֻ���¶����з�nullֵ���ֶ�
     * @param b2bSupplierCardAccount ���ݶ���
     * @return �ɹ����µļ�¼��
     */
    public int updateByPrimaryKeySelective(B2bSupplierCardAccountBean b2bSupplierCardAccount);

    /**
    * ��������
    * @param sql �������
    * @param args ����
    * @return �ɹ����µļ�¼��
    */
    public int update(String sql, Object... args);

    /**
    * ��������
    * @param sql �������
    * @param pss ����
    * @return �ɹ����µļ�¼��
    */
    public int update(String sql, PreparedStatementSetter pss);

    /**
    * ��������ɾ����¼
    * @param id ����
    * @return �ɹ�ɾ���ļ�¼��
    */
    public int deleteByPrimaryKey(String supCardAcntId);

    /**
     * ����sql������ɾ��
     * @param whereSql where�ؼ��ֺ����ɾ�����������磺id = ?
     * @param params �����еĲ�����������Ҫ��"?"��������һ��
     * @return �ɹ�ɾ���ļ�¼��
     */
    public int deleteByWhereSql(String whereSql, Object[] params);
}

