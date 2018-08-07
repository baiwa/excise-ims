import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { AjaxService } from '../../../../../common/services';
import { TextDateTH, formatter } from '../../../../../common/helper/datepicker';
import { ActivatedRoute } from "@angular/router";
declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-ts01-11',
  templateUrl: './ts01-11.component.html',
  styleUrls: ['./ts01-11.component.css']
})
export class Ts0111Component implements OnInit {
  obj: Ts0111;
  beans: Bean[];
  @Output() discard = new EventEmitter<any>();

  numbers: number[];

  constructor(private ajax: AjaxService,private route: ActivatedRoute,) {
    this.obj = new Ts0111();
    this.beans = [new Bean];
    this.numbers = [1, 2, 3];
    this.obj.exciseId = this.route.snapshot.queryParams["exciseId"];
  }

  ngOnInit() {
    $('#date1').calendar({
      type: 'date',
      text: TextDateTH,
      formatter: formatter('วดป')
    });
    $('#date2').calendar({
      type: 'date',
      text: TextDateTH,
      formatter: formatter('วดป')
    });
    $("#time").calendar({
      type: "time",
      text: TextDateTH,
      formatter: formatter('เวลา')
    });
  }

  onDiscard = () => {
    // on click this view hide them
    // ... Don't change or delete this
    this.discard.emit(false);
  };

  /*onAddField = () => {
    let num = this.numbers[this.numbers.length-1];
    this.numbers.push(num+1);
    this.numbers.sort();
  };*/

  /* onDelField = index => {
     this.numbers.splice(index, 1);
     this.numbers.sort();
   };*/

  onAddField = () => {
    this.beans.push(new Bean());

  };

  onDelField = index => {
    this.beans.splice(index, 1);
  };


  optionAddress = () => {
    const optionURL = "excise/detail/objectAddressByExciseId";
    this.ajax.post(optionURL, {
      exciseId: this.obj.exciseId
    }, res => {
      console.log(res.json());
      var dat = res.json();
      this.obj.homeNumber = dat.homeNumber;
      this.obj.moo = dat.moo;
      this.obj.byWay = dat.byWay;
      this.obj.street = dat.street;
      this.obj.tambol = dat.tambol;
      this.obj.district = dat.district;
      this.obj.province = dat.province;
      this.obj.zipCode = dat.zipCode;
    });
  };


  onSubmit = e => {
    e.preventDefault();
    const url = "report/pdf/ts/mis_t_s_01_11";
    this.obj.Bean = this.beans;

    // Date
    let date1 = $("#date1 .ui input").val().split(" ");
    this.obj.day = date1[0];
    this.obj.month = date1[1];
    this.obj.year = date1[2];
    this.obj.date = $("#date2 .ui input").val();
    // Times
    this.obj.time = $("#time .ui input").val();


    if (this.obj.agreeBox1 == "factory") {
      this.obj.factory = true;
      this.obj.service = false;
      this.obj.company = false;
    } else if (this.obj.agreeBox1 == "service") {
      this.obj.factory = false;
      this.obj.service = true;
      this.obj.company = false;
    } else if (this.obj.agreeBox1 == "company") {
      this.obj.factory = false;
      this.obj.service = false;
      this.obj.company = true;
    }

    if (this.obj.agreeBox2 == "owner") {
      this.obj.owner = true;
      this.obj.director = false;
      this.obj.partner = false;
      this.obj.attorney = false;
      this.obj.other = false;
    } else if (this.obj.agreeBox2 == "director") {
      this.obj.owner = false;
      this.obj.director = true;
      this.obj.partner = false;
      this.obj.attorney = false;
      this.obj.other = false;
    } else if (this.obj.agreeBox2 == "partner") {
      this.obj.owner = false;
      this.obj.director = false;
      this.obj.partner = true;
      this.obj.attorney = false;
      this.obj.other = false;
    } else if (this.obj.agreeBox2 == "attorney") {
      this.obj.owner = false;
      this.obj.director = false;
      this.obj.partner = false;
      this.obj.attorney = true;
      this.obj.other = false;
    } else if (this.obj.agreeBox2 == "other") {
      this.obj.owner = false;
      this.obj.director = false;
      this.obj.partner = false;
      this.obj.attorney = false;
      this.obj.other = true;
    }

    this.ajax.post(url, `'${JSON.stringify(this.obj).toString()}'`, res => {
      if (res.status == 200 && res.statusText == "OK") {
        window.open("/ims-webapp/api/report/pdf/mis_t_s_01_11/file");
      }
    });
  };

}
class Ts0111 {
  logo: string = "logo.jpg";
  [x: string]: any;

  office: string;
  day: string;
  month: string;
  year: string;
  time: string;
  name1: string;
  position: string;
  tax: string;
  exciseId: string;
  agreeBox1: string;
  factory: boolean;
  service: boolean;
  company: boolean;

  homeNumber: string;
  moo: string;
  byWay: string;
  street: string;
  tambol: string;
  district: string;
  province: string;
  zipCode: string;
  name2: string;

  agreeBox2: string;
  owner: boolean;
  director: boolean;
  partner: boolean;
  attorney: boolean;
  other: boolean;
  otherTxt: string;

  book: string;
  atNumber1: string;
  atNumber2: string;
  date: string;
  donor: string;
  witness1: string;
  witness2: string;
}
class Bean {
  [x: string]: any;

  list: string;
  number: string;
  note: string;
}
