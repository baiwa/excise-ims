import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'ope04-4-1',
  templateUrl: './ope04-4-1.component.html',
  styleUrls: ['./ope04-4-1.component.css']
})
export class Ope0441Component implements OnInit {

  public showData: boolean = false;
  breadcrumb: BreadCrumb[] = [    
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'สร้างกระดาษทำการตรวจสอบภาษี', route: '#' },
    { label: 'สร้างกระดาษทำการสัมพันธ์การเบิกใช้วัตถุดิบกับการรับสินค้าสำเร็จรูป', route: '#' },    
  ];
  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-04410');

  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

}
