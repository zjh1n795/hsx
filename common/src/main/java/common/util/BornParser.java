package common.util;
//package com.trm.util;
//public class BornParser {   
//      
//    public void born(String packageName,Class<?> clazz,String[] titles,String[] properties) {   
//      
//        StringBuffer sb = new StringBuffer();   
//      
//        sb.append("package " + packageName + ";\n\n");   
//      
//        sb.append("public class Parse" + clazz.getSimpleName() + " {\n\n");   
//      
//        for (int index = 0; index < properties.length; index++) {   
//      
//            try {   
//      
//                Field field = clazz.getDeclaredField(properties[index]);   
//      
//                if (titles != null) {   
//      
//                    sb.append("\t/**\n\t*" + titles[index] + "\n\t*/\n");   
//      
//                }   
//      
//                sb.append("\tpublic String get" + StringHelper.upperCaseFirstChar(properties[index]) + "(" + field.getType().getSimpleName() + " " + properties[index] + "){\n");   
//      
//                sb.append("\t\t return " + properties[index] + ";\n\t}\n\n");   
//      
//            } catch (SecurityException e) {   
//      
//                e.printStackTrace();   
//      
//            } catch (NoSuchFieldException e) {   
//      
//                e.printStackTrace();   
//      
//            }   
//      
//        }   
//      
//        sb.append("}");   
//      
//        System.out.println(sb.toString());   
//      
//        new FileUtil().append("D:\\crm\\src\\main\\java\\net\\eziep\\pojo\\user\\parse\\Parse" + clazz.getSimpleName() + ".java", sb.toString(), false);   
//      
//    }   
//          
//    /*public static void main(String[] args) {   
//      
//        String packageName = "net.eziep.pojo.user.parse";   
//      
//        Class<?> clazz = User.class;   
//      
//        String[] titles = { "手机号码 ", "姓名"};   
//      
//        String[] properties = { "mobile", "name" };   
//              
//        new BornParser().born(packageName, clazz, titles, properties);   
//              
//    }*/
//      
//}