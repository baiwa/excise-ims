import { Component, OnInit } from '@angular/core';
import { AjaxService } from 'services/ajax.service';
import { TextDateTH, formatter } from 'helpers/datepicker';

const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId"
};
declare var $: any;
@Component({
  selector: 'app-int06-12',
  templateUrl: './int06-12.component.html',
  styleUrls: ['./int06-12.component.css']
})
export class Int0612Component implements OnInit {

  private selectedProduct: string = "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก ";
  private productList: any[];

  travelTo1List: any;
  travelTo2List: any;
  travelTo3List: any;

  constructor(private ajax: AjaxService) { }

  ngOnInit() {
    $('.menu .item')
    .tab()
    ;
    
    this.hidedata();

   
    $("#calendar1").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()

    });

    $("#calendar2").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });

    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.search").css("width", "100%");
    this.productList = [
      { value: "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก " },
      { value: "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก " },
      { value: "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก " },
      { value: "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก " },
      { value: "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก " },
      { value: "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก " }
    ];
    this.travelTo1Dropdown();
  }

  travelTo1Dropdown = () => {
    this.ajax.post(URL.DROPDOWN, { type: "SECTOR_VALUE" }, res => {
      this.travelTo1List = res.json();
    });
  }

  travelTo2Dropdown = e => {
    var id = e.target.value;
    if (id != "") {
      this.ajax.post(URL.DROPDOWN, { type: "SECTOR_VALUE", lovIdMaster: id }, res => {
        this.travelTo2List = res.json();
      });
    }
  }

  travelTo3Dropdown = e => {
    var id = e.target.value;
    if (id != "") {
      this.ajax.post(URL.DROPDOWN, { type: "SECTOR_VALUE", lovIdMaster: id }, res => {
        this.travelTo3List = res.json();
      });
    }
  }

  hidedata() {
    $('#hideData').hide();
  }
  showdata() {
    $('#hideData').show();
  }

  popupEditData() {
    $('#modal1').modal('show');


  }

  closePopupEdit() {
    $('#modal1').modal('hide');
   
  }
  
  popupEditData2() {
    $('#modal1').modal('show');


  }

  closePopupEdit2() {
    $('#modal1').modal('hide');
   
  }



}

