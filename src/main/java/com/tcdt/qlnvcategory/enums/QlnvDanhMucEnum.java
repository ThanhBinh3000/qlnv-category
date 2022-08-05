package com.tcdt.qlnvcategory.enums;

public enum QlnvDanhMucEnum {
        HOAT_DONG("01", "Hoạt Động", "Hoạt Động"),

        NGUNG_HOAT_DONG("00", "Ngưng Hoạt Động", "Ngưng Hoạt Động"),
        ;

        private final String id;

        private final String ten;

        private final String trangThaiDuyet;


        QlnvDanhMucEnum(String id, String ten, String trangThaiDuyet) {
                this.id = id;
                this.ten = ten;
                this.trangThaiDuyet = trangThaiDuyet;
        }

        public String getId() {
                return id;
        }

        public String getTen() {
                return ten;
        }

        public String getTrangThaiDuyet() {
                return trangThaiDuyet;
        }

        public static String getTrangThaiDuyetById(String id){
                for (QlnvDanhMucEnum status : QlnvDanhMucEnum.values()) {
                        if (status.getId().equals(id))
                                return status.getTrangThaiDuyet();
                }

                return null;
        }
}
