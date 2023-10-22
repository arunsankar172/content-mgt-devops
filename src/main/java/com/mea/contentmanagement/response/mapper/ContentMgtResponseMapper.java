package com.mea.contentmanagement.response.mapper;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mea.contentmanagement.model.Control;

@Service
public class ContentMgtResponseMapper {
	
    public List<Control> mapControlToControlModel(List<com.mea.contentmanagement.domain.Control> controlDomain) {
        if ( controlDomain == null ) {
            return null;
        }

        List<Control> list = new ArrayList<Control>( controlDomain.size() );
        for ( com.mea.contentmanagement.domain.Control control : controlDomain ) {
            list.add( controlToControl( control ) );
        }

        return list;
    }
    
    
    
    protected Control controlToControl(com.mea.contentmanagement.domain.Control control) {
        if ( control == null ) {
            return null;
        }

        Control control1 = new Control();

        control1.setControlCode( control.getControlCode() );
        control1.setControlId( control.getControlId() );
        control1.setControlType( control.getControlType() );
        control1.setCreatedBy( control.getCreatedBy() );
        control1.setCreatedDateTime( convertTimeStampToString (control.getCreatedDateTime() ));
        control1.setLocaleKey( control.getLocaleKey() );
        control1.setLocaleValueAr( control.getLocaleValueAr() );
        control1.setLocaleValueEn( control.getLocaleValueEn() );
        control1.setNewValueAr( control.getNewValueAr() );
        control1.setNewValueEn( control.getNewValueEn() );
        control1.setPageId( control.getPageId() );
        control1.setScreen( control.getScreen() );
        control1.setStatus( control.getStatus() );
        control1.setUpdatedBy( control.getUpdatedBy() );
        control1.setUpdatedDateTime(convertTimeStampToString (control.getUpdatedDateTime() ) );

        return control1;
    }
    
	private static String convertTimeStampToString(Timestamp time) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		 Date date = new Date(time.getTime());
	     return  dateFormat.format(date);
	}

}
