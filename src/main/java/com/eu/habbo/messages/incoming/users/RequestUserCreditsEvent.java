package com.eu.habbo.messages.incoming.users;

import com.eu.habbo.Emulator;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.users.UserCreditsComposer;
import com.eu.habbo.messages.outgoing.users.UserCurrencyComposer;
import com.eu.habbo.messages.outgoing.users.UserPointsComposer;

public class RequestUserCreditsEvent extends MessageHandler
{
    @Override
    public void handle()
    {
        this.client.sendResponse(new UserCreditsComposer(this.client.getHabbo()));
        this.client.sendResponse(new UserCurrencyComposer(this.client.getHabbo()));
    }
}
