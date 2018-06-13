import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Int092Component } from './int09-2.component';

describe('Int092Component', () => {
  let component: Int092Component;
  let fixture: ComponentFixture<Int092Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Int092Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Int092Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
