import { Component, OnInit, AfterViewInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { AuthService } from 'services/auth.service';
import { RentHouseRule, Int0613Service } from './int06-13.service';
import { FormGroup, FormArray, FormBuilder, FormControl } from '@angular/forms';

@Component({
  selector: 'app-int06-13',
  templateUrl: './int06-13.component.html',
  styleUrls: ['./int06-13.component.css']
})
export class Int0613Component implements OnInit, AfterViewInit {

  breadcrumb: BreadCrumb[];
  data: RentHouseRule[];

  constructor(
    private authService: AuthService,
    private service: Int0613Service,
    private formBuilder: FormBuilder
  ) {

    // BreadCrumb
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "กำหนดค่าเช่าบ้านข้าราชการ", route: "#" },
    ];

  }

  ngOnInit(): void {

    this.authService.reRenderVersionProgram('INT-061300'); // Page Version

    // this.service.getRent().then(data => {
    //   this.data = data; // Data from Backend
    // });

  }

  ngAfterViewInit(): void { }

}
