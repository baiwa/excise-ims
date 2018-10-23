(function( $ ) {
    $.fn.DataTableTh = function(options) {
        var opt = {
            ordering: false,
            searching: false,
            scrollX: true,
            language: {
                info: "แสดงจาก_START_ ถึง _END_ จากทั้งหมด _TOTAL_ รายการ",
                paginate: {
                  first: "หน้าแรก",
                  last: "หน้าสุดท้าย",
                  next: "ถัดไป",
                  previous: "ก่อนหน้า"
                },
                lengthMenu: "แสดง _MENU_ รายการ",
                loadingRecords: "กำลังดาวน์โหลด...",
                processing: "กำลังประมวลผล...",
                search: "ค้นหาทั้งหมด",
                infoEmpty: "แสดงจาก 0 ถึง 0 จากทั้งหมด 0 รายการ",
                emptyTable: "ไม่พบข้อมูล"
              }
        };
        var settings = $.extend( opt ,options);
  
       return  $(this).DataTable(settings);
    };
  })( jQuery );