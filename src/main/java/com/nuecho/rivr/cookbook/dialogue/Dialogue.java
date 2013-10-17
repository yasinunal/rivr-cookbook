/*
 * Copyright (c) 2013 Nu Echo Inc. All rights reserved.
 */

package com.nuecho.rivr.cookbook.dialogue;

import org.w3c.dom.*;

import com.nuecho.rivr.core.dialogue.*;
import com.nuecho.rivr.core.util.*;
import com.nuecho.rivr.voicexml.dialogue.*;
import com.nuecho.rivr.voicexml.turn.first.*;
import com.nuecho.rivr.voicexml.turn.last.*;
import com.nuecho.rivr.voicexml.turn.output.*;
import com.nuecho.rivr.voicexml.turn.output.audio.*;

/**
 * A message turn is a primitive to <i>queue</i> a message on the VoiceXML
 * platform. The message is not played immediately, but rather when the VoiceXML
 * platform decides to do so.
 * 
 * @see <a href="http://www.w3.org/TR/voicexml20/#dml4.1.8">VoiceXML
 *      specification, 4.1.8 Prompt Queueing and Input Collection</a>
 * @author Nu Echo Inc.
 */
public class Dialogue implements VoiceXmlDialogue {

    @Override
    public VoiceXmlLastTurn run(VoiceXmlFirstTurn firstTurn, VoiceXmlDialogueContext context)
            throws Exception {

        //Play SSML content
        Document document = DomUtils.createDocument();

        Element phonemeElement = document.createElement("phoneme");
        phonemeElement.setAttribute("ph", "'rI.v@r");
        phonemeElement.setAttribute("alphabet", "x-sampa");
        phonemeElement.appendChild(document.createTextNode("Rivr"));

        DocumentFragment ssmlFragment = document.createDocumentFragment();
        ssmlFragment.appendChild(phonemeElement);

        Message message = new Message("ssml-message", new SpeechSynthesis(ssmlFragment));
        DialogueUtils.doTurn(message, context);

        //end of dialogue
        return new Exit("exit");
    }
}
