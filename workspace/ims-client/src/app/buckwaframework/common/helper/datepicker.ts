export var TextDateTH = {
    days: ['อา', 'จ', 'อ', 'พ', 'พฤ', 'ศ', 'ส'],
    months: ['มกราคม', 'กุมภาพันธ์', 'มีนาคม', 'เมษายน', 'พฤษภาคม', 'มิถุนายน', 'กรกฎาคม', 'สิงหาคม', 'กันยายน', 'ตุลาคม', 'พฤศจิกายน', 'ธันวาคม'],
    monthsShort: ['ม.ค.', 'ก.พ.', 'มี.ค.', 'เม.ย.', 'พ.ค.', 'มิ.ย.', 'ก.ค.', 'ส.ค.', 'ก.ย.', 'ต.ค.', 'พ.ย.', 'ธ.ค.'],
    today: 'วันนี้',
    now: 'เดี๋ยวนี้',
    am: 'ก่อนบ่าย',
    pm: 'หลังบ่าย'
};
 
export var digit = number => {
    return (number < 10 ? '0' : '') + number;
}

export var formatter = (what: string = '') => {
    switch(what) {
        case 'day':
            return {
                date: function (date , settings) {
                    if (!date) return '';
                    let day = date.getDate();
                    return digit(day);
                }
            }
        case 'month':
            return {
                date: function (date , settings) {
                    if (!date) return '';
                    let month = date.getMonth() + 1;
                    return digit(month);
                }
            }
        case 'year':
            return {
                date: function (date , settings) {
                    if (!date) return '';
                    let year = date.getFullYear() + 543;
                    return year;
                }
            }
        case 'day-month':
            return {
                date: function (date , settings) {
                    if (!date) return '';
                    let day = date.getDate();
                    let month = date.getMonth() + 1;
                    return digit(day) + '/' + digit(month);
                }
            }
        case 'month-year':
            return {
                date: function (date , settings) {
                    if (!date) return '';
                    let month = date.getMonth() + 1;
                    let year = date.getFullYear() + 543;
                    return digit(month) + '/' + year;
                }
            }
        default:
            return {
                date: function (date , settings) {
                    if (!date) return '';
                    let day = date.getDate();
                    let month = date.getMonth() + 1;
                    let year = date.getFullYear() + 543;
                    return digit(day) + '/' + digit(month) + '/' + year;
                }
            }
    }
}

export var ThaiFormatter = (date) => {
    const options = {  year: 'numeric', month: 'long', day: 'numeric' };
    return date.toLocaleDateString('th-TH', options);
}

export default { TextDateTH, formatter, digit, ThaiFormatter }