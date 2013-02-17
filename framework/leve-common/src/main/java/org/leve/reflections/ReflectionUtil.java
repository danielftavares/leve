package org.leve.reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.list.dsl.Matcher;
import net.vidageek.mirror.list.dsl.MirrorList;

public class ReflectionUtil {
	private static final Mirror mirror = new Mirror();
	
	public static void setFieldValue(Object bean, Field f, Object val) {
		mirror.on(bean).set().field(f).withValue(val);
	}
	
	public static Object getFieldValue(Object bean, Field f) {
		return mirror.on(bean).get().field(f);
	}
	
	public static Object getFieldValueWithAnnotation(Object bean, final Class<? extends Annotation> annotationClass) {
		Field field = getFieldWithAnnotation(bean.getClass(), annotationClass);
		if(field != null){
			return mirror.on(bean).get().field(field);
		}
		return null;
	}
	
	public static Field getFieldWithAnnotation(Class<?> type, final Class<? extends Annotation> annotationClass) {
		Field field = null;

		MirrorList<Field> list = mirror.on(type).reflectAll()
				.fields().matching(new Matcher<Field>() {

					@Override
					public boolean accepts(Field element) {
						return element.isAnnotationPresent(annotationClass);
					}
				});
		if (!list.isEmpty()) {
			field = list.get(0);
		}

		return field;
	}
	
	public static Field getFieldWithAnnotation(String type, final Class<? extends Annotation> annotationClass) {
		Field field = null;

		MirrorList<Field> list = mirror.on(type).reflectAll()
				.fields().matching(new Matcher<Field>() {

					@Override
					public boolean accepts(Field element) {
						return element.isAnnotationPresent(annotationClass);
					}
				});
		if (!list.isEmpty()) {
			field = list.get(0);
		}

		return field;
	}
	
	public static Field getField(Class<?> type, String attribute){
		return mirror.on(type).reflect().field(attribute);
	}
	
	public static Field getField(String type, String attribute){
		return mirror.on(type).reflect().field(attribute);
	}

	/**
	 * retorna a anotacao declarada no campo
	 * @param clazz
	 * @param attribute
	 * @param annotationClass
	 * @return
	 */
	public static <T extends Annotation> T getAnnotationField(String clazz, String attribute, final Class<T> annotationClass) {
		return getAnnotationField(mirror.on(clazz).reflect().field(attribute), annotationClass);
	}

	public static <T extends Annotation> T getAnnotationField(Class<?> clazz, String attribute,
			 final Class<T> annotationClass) {
		return getAnnotationField(mirror.on(clazz).reflect().field(attribute), annotationClass);
	}
	
	
	public static <T extends Annotation> T getAnnotationField(Field f,
			 final Class<T> annotationClass) {
		T annotation = null;
		if(f != null){
			annotation = mirror.on(f).reflect().annotation(annotationClass);
		}
		return annotation;
	}
	
	public static Class<?> getFieldGenericType(Field f){
		ParameterizedType stringListType = (ParameterizedType) f.getGenericType();
		return (Class<?>) stringListType.getActualTypeArguments()[0];
	}

}