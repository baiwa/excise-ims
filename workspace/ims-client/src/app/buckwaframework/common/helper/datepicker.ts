import { ThaiNumber } from ".";

export var TextDateTH = {
  days: ["อา", "จ", "อ", "พ", "พฤ", "ศ", "ส"],
  months: [
    "มกราคม",
    "กุมภาพันธ์",
    "มีนาคม",
    "เมษายน",
    "พฤษภาคม",
    "มิถุนายน",
    "กรกฎาคม",
    "สิงหาคม",
    "กันยายน",
    "ตุลาคม",
    "พฤศจิกายน",
    "ธันวาคม"
  ],
  monthsShort: [
    "ม.ค.",
    "ก.พ.",
    "มี.ค.",
    "เม.ย.",
    "พ.ค.",
    "มิ.ย.",
    "ก.ค.",
    "ส.ค.",
    "ก.ย.",
    "ต.ค.",
    "พ.ย.",
    "ธ.ค."
  ],
  today: "วันนี้",
  now: "เดี๋ยวนี้",
  am: "ก่อนบ่าย",
  pm: "หลังบ่าย"
};

export var digit = number => {
  return (number < 10 ? "0" : "") + number;
};

export var formatter = (what: string = "") => {
  switch (what) {
    case "เวลา":
      return {
        time: function (date, settings) {
          if (!date) return "";
          var now = date,
            h = now.getHours(),
            m = now.getMinutes(),
            s = now.getSeconds();
          return digit(h) + ':' + digit(m); // + ':' + digit(s);
        }
      };
    case "ว":
      return {
        date: function (date, settings) {
          if (!date) return "";
          let day = date.getDate();
          return digit(day);
        }
      };
    case "ด":
      return {
        date: function (date, settings) {
          if (!date) return "";
          let month = date.getMonth();
          return TextDateTH.months[month];
        }
      };
    case "ป":
      return {
        date: function (date, settings) {
          if (!date) return "";
          let year = date.getFullYear() + 543;
          return year.toString();
        }
      };
    case "วดป":
      return {
        header: function (date, mode, settings) {
          //return a string to show on the header for the given 'date' and 'mode'
          if (!date) return "";
          let month = date.getMonth();
          let year = date.getFullYear() + 543;
          return TextDateTH.months[month] + " " + year;
        },
        date: function (date, settings) {
          if (!date) return "";
          let day = date.getDate();
          let month = date.getMonth();
          let year = date.getFullYear() + 543;
          return digit(day) + " " + TextDateTH.months[month] + " " + year.toString();
        }
      };
    case "day":
      return {
        date: function (date, settings) {
          if (!date) return "";
          let day = date.getDate();
          return digit(day);
        }
      };
    case "month":
      return {
        date: function (date, settings) {
          if (!date) return "";
          let month = date.getMonth() + 1;
          return digit(month);
        }
      };
    case "year":
      return {
        header: function (date, mode, settings) {
          //return a string to show on the header for the given 'date' and 'mode'
          return date.getFullYear() + 543;
        },
        cell: function (cell, date, cellOptions) {
          //customize the calendar cell, cellOptions is:
          //{ mode: string, adjacent: boolean, disabled: boolean, active: boolean, today: boolean }
          cell[0].innerHTML = parseInt(cell[0].innerHTML) + 543;
        },
        date: function (date, settings) {
          if (!date) return "";
          let year = date.getFullYear() + 543;
          return year;
        }
      };
    case "day-month":
      return {
        date: function (date, settings) {
          if (!date) return "";
          let day = date.getDate();
          let month = date.getMonth() + 1;
          return digit(day) + "/" + digit(month);
        }
      };
    case "month-year":
      return {
        header: function (date, mode, settings) {
          //return a string to show on the header for the given 'date' and 'mode'
          return date.getFullYear() + 543;
        },
        date: function (date, settings) {
          if (!date) return "";
          let month = date.getMonth() + 1;
          let year = date.getFullYear() + 543;
          return digit(month) + "/" + year;
        }
      };
    default:
      return {
        header: function (date, mode, settings) {
          //return a string to show on the header for the given 'date' and 'mode'
          const year = date.getFullYear() + 543;
          const month = TextDateTH.months[date.getMonth()];
          return `${month} ${year}`;
        },
        date: function (date, settings) {
          if (!date) return "";
          let day = date.getDate();
          let month = date.getMonth() + 1;
          let year = date.getFullYear() + 543;
          return digit(day) + "/" + digit(month) + "/" + year;
        }
      };
  }
};

export var ThaiFormatter = date => {
  const options = { year: "numeric", month: "long", day: "numeric" };
  return date.toLocaleDateString("th-TH", options);
};

export default { TextDateTH, formatter, digit, ThaiFormatter };
