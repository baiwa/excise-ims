import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Int0837Component } from './int08-3-7.component';

describe('Int0837Component', () => {
  let component: Int0837Component;
  let fixture: ComponentFixture<Int0837Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Int0837Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Int0837Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
