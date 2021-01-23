import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { FormService } from 'app/entities/form/form.service';
import { IForm, Form } from 'app/shared/model/form.model';
import { CompetitorName } from 'app/shared/model/enumerations/competitor-name.model';
import { Province } from 'app/shared/model/enumerations/province.model';
import { ProductType } from 'app/shared/model/enumerations/product-type.model';
import { TypeOfOffer } from 'app/shared/model/enumerations/type-of-offer.model';
import { OfferType } from 'app/shared/model/enumerations/offer-type.model';
import { OfferPurchaseLimitation } from 'app/shared/model/enumerations/offer-purchase-limitation.model';
import { NatureOFOffer } from 'app/shared/model/enumerations/nature-of-offer.model';
import { OfferValidity } from 'app/shared/model/enumerations/offer-validity.model';
import { RiskOfMarketShareLoss } from 'app/shared/model/enumerations/risk-of-market-share-loss.model';

describe('Service Tests', () => {
  describe('Form Service', () => {
    let injector: TestBed;
    let service: FormService;
    let httpMock: HttpTestingController;
    let elemDefault: IForm;
    let expectedResult: IForm | IForm[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FormService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Form(
        0,
        CompetitorName.MTN,
        Province.Gauteng,
        'image/png',
        'AAAAAAA',
        currentDate,
        ProductType.Fibre,
        TypeOfOffer.LocationBased,
        OfferType.Prepaid,
        OfferPurchaseLimitation.All,
        NatureOFOffer.RechargOffer,
        0,
        OfferValidity.OneDay,
        0,
        currentDate,
        RiskOfMarketShareLoss.Low
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            offerDate: currentDate.format(DATE_TIME_FORMAT),
            offerEndDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Form', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            offerDate: currentDate.format(DATE_TIME_FORMAT),
            offerEndDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            offerDate: currentDate,
            offerEndDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Form()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Form', () => {
        const returnedFromService = Object.assign(
          {
            competitor: 'BBBBBB',
            province: 'BBBBBB',
            image: 'BBBBBB',
            offerDate: currentDate.format(DATE_TIME_FORMAT),
            productType: 'BBBBBB',
            typeOfOffer: 'BBBBBB',
            offerType: 'BBBBBB',
            offerPurchaseLimitation: 'BBBBBB',
            natureOFOffer: 'BBBBBB',
            offerPrice: 1,
            offerValidity: 'BBBBBB',
            offerAllocation: 1,
            offerEndDate: currentDate.format(DATE_TIME_FORMAT),
            riskOfMarketShareLoss: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            offerDate: currentDate,
            offerEndDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Form', () => {
        const returnedFromService = Object.assign(
          {
            competitor: 'BBBBBB',
            province: 'BBBBBB',
            image: 'BBBBBB',
            offerDate: currentDate.format(DATE_TIME_FORMAT),
            productType: 'BBBBBB',
            typeOfOffer: 'BBBBBB',
            offerType: 'BBBBBB',
            offerPurchaseLimitation: 'BBBBBB',
            natureOFOffer: 'BBBBBB',
            offerPrice: 1,
            offerValidity: 'BBBBBB',
            offerAllocation: 1,
            offerEndDate: currentDate.format(DATE_TIME_FORMAT),
            riskOfMarketShareLoss: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            offerDate: currentDate,
            offerEndDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Form', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
