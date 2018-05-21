export var TextDateTH = {
    days: ['อา', 'จ', 'อ', 'พ', 'พฤ', 'ศ', 'ส'],
    months: ['มกราคม', 'กุมภาพันธ์', 'มีนาคม', 'เมษายน', 'พฤษภาคม', 'มิถุนายน', 'กรกฎาคม', 'สิงหาคม', 'กันยายน', 'ตุลาคม', 'พฤศจิกายน', 'ธันวาคม'],
    monthsShort: ['ม.ค.', 'ก.พ.', 'มี.ค.', 'เม.ย.', 'พ.ค.', 'มิ.ย.', 'ก.ค.', 'ส.ค.', 'ก.ย.', 'ต.ค.', 'พ.ย.', 'ธ.ค.'],
    today: 'วันนี้',
    now: 'เดี๋ยวนี้',
    am: 'ก่อนบ่าย',
    pm: 'หลังบ่าย'
};
 
var digit = number => {
    return (number < 10 ? '0' : '') + number;
}

export var formatter = {
    date: function (date , settings) {
        if (!date) return '';
        let day = date.getDate();
        let month = date.getMonth() + 1;
        let year = date.getFullYear() + 543;
        return digit(day) + '/' + digit(month) + '/' + year;
    }
};

export default { TextDateTH, formatter }