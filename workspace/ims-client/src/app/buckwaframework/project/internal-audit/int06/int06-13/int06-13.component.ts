import { Component, OnInit, AfterViewInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { AjaxService } from 'services/ajax.service';
import { AuthService } from 'services/auth.service';

declare var $: any;

@Component({
  selector: 'app-int06-13',
  templateUrl: './int06-13.component.html',
  styleUrls: ['./int06-13.component.css']
})
export class Int0613Component implements OnInit , AfterViewInit {
  [x: string]: any;

  breadcrumb: BreadCrumb[]

  
  constructor(
    private authService: AuthService,
    private ajax: AjaxService,
  ) { 
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "กำหนดค่าเช่าบ้านข้าราชการ", route: "#" },
    ];
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-061300');
    this.test();
  }

  ngAfterViewInit(): void {
    
  }

  test() {
    const URL = "ia/int061300/findManagementType";
    this.ajax.get(URL,res => {
      console.log(res.json());
    }
    );

  }


}
