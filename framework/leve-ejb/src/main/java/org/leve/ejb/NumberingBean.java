package org.leve.ejb;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.leve.annotation.LeveKey;
import org.leve.beans.Numbering;
import org.leve.beans.NumberingFindDto;
import org.leve.reflections.ReflectionUtil;

@Stateless
public class NumberingBean extends BaseBusinessBean<Numbering, NumberingFindDto> {

	@EJB
	private NumberingDAO numberingDAO;

	@Override
	protected NumberingDAO getDAO() {
		return numberingDAO;
	}

	@Override
	public Numbering create(Numbering bean) {
		return getDAO().create(bean);
	}
	
	public <T> T generateKey(T bean) {
		Field field = ReflectionUtil.getFieldWithAnnotation(bean.getClass(),
				LeveKey.class);
		if (field != null) {
			String val = (String) ReflectionUtil.getFieldValue(bean, field);
			if (val == null || val.toString().trim().isEmpty()) {
				String newVal = generateValue(bean.getClass());
				ReflectionUtil.setFieldValue(bean, field, newVal);
			}
		}
		return bean;
	}

	private String generateValue(Class<?> clazz) {
		
		Numbering numbering = getDAO().findByKey(clazz.getName());
		String newVal = null;
		
		if(numbering != null){
			newVal = newValue(numbering);
			update(numbering);
		}
		return newVal;
		
	}
	
	private String newValue(Numbering numbering) {

		StringBuilder m_sId = new StringBuilder();

		numbering.setCounter(numbering.getCounter() + 1);

		if (numbering.getFormat().equals("")) {
			DecimalFormat df = new DecimalFormat("0000");
			m_sId = m_sId.append(numbering.getYear()).append(df.format(numbering.getCounter()));
		} else {
			StringTokenizer st = new StringTokenizer(numbering.getFormat(), "+",
					false);
			while (st.hasMoreTokens()) {
				String m_sPart = "";
				m_sPart = st.nextToken();

				if (m_sPart.equalsIgnoreCase("yy")) {
					m_sId.append(Integer.toString(numbering.getYear()).substring(2));
				} else if (m_sPart.equalsIgnoreCase("yyyy")) {
					m_sId.append(numbering.getYear());
				} else if (m_sPart.startsWith("0")) {
					DecimalFormat df = new DecimalFormat(m_sPart);
					m_sId.append(df.format(numbering.getCounter()));
				} else {
					m_sId.append(m_sPart);
				}
			}
		}

		return m_sId.toString();
	}

}