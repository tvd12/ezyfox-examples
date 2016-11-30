/**
 * 
 */
package com.tvd12.ezyfox.videopoker.mapper;

import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.videopoker.request.BetRequestListener;

/**
 * @author tavandung12
 *
 */
public class BetRequestDeserializer implements ObjectDeserializer<BetRequestListener> {

    @Override
    public BetRequestListener deserialize(BetRequestListener object, Parameters params) {
        object.setBettingTypeId(params.get("1", int.class));
        return object;
    }

}
