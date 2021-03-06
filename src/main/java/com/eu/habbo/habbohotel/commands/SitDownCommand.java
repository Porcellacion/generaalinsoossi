package com.eu.habbo.habbohotel.commands;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.habbohotel.users.Habbo;
import gnu.trove.iterator.TIntObjectIterator;
import gnu.trove.map.TIntObjectMap;

import java.util.NoSuchElementException;

public class SitDownCommand extends Command
{
    public SitDownCommand()
    {
        super("cmd_sitdown", Emulator.getTexts().getValue("commands.keys.cmd_sitdown").split(";"));
    }
    @Override
    public boolean handle(GameClient gameClient, String[] params) throws Exception
    {
        for (Habbo habbo : gameClient.getHabbo().getHabboInfo().getCurrentRoom().getHabbos())
        {
            if(habbo.getRoomUnit().isWalking())
            {
                habbo.getRoomUnit().stopWalking();
            }
            else if(habbo.getRoomUnit().getStatus().containsKey("sit"))
            {
                continue;
            }

            gameClient.getHabbo().getHabboInfo().getCurrentRoom().makeSit(habbo);
        }
        
        return true;
    }
}
