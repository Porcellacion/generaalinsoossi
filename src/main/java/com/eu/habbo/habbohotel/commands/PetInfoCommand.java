package com.eu.habbo.habbohotel.commands;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.habbohotel.pets.AbstractPet;
import com.eu.habbo.habbohotel.pets.Pet;
import com.eu.habbo.habbohotel.pets.PetManager;
import com.eu.habbo.habbohotel.rooms.RoomChatMessage;
import com.eu.habbo.habbohotel.rooms.RoomChatMessageBubbles;
import com.eu.habbo.messages.outgoing.generic.alerts.GenericAlertComposer;
import com.eu.habbo.messages.outgoing.rooms.users.RoomUserWhisperComposer;
import gnu.trove.procedure.TIntObjectProcedure;

public class PetInfoCommand extends Command
{
    public PetInfoCommand()
    {
        super("cmd_pet_info", Emulator.getTexts().getValue("commands.keys.cmd_pet_info").split(";"));
    }

    @Override
    public boolean handle(GameClient gameClient, String[] params) throws Exception
    {
        if(params.length > 1)
        {
            if(gameClient.getHabbo().getHabboInfo().getCurrentRoom() == null)
                return false;

            String name = params[1];

            gameClient.getHabbo().getHabboInfo().getCurrentRoom().getCurrentPets().forEachEntry(new TIntObjectProcedure<AbstractPet>()
            {
                @Override
                public boolean execute(int a, AbstractPet pet)
                {
                    if(pet.getName().equalsIgnoreCase(name))
                    {
                        if(!(pet instanceof Pet))
                            return true;

                        gameClient.sendResponse(new GenericAlertComposer("" +
                                Emulator.getTexts().getValue("commands.generic.cmd_pet_info.title") + ": " + pet.getName() + "\r\n" +
                                Emulator.getTexts().getValue("generic.pet.id") + ": " + pet.getId() + "\r" +
                                Emulator.getTexts().getValue("generic.pet.name") + ": " + pet.getName() + "\r" +
                                Emulator.getTexts().getValue("generic.pet.age") + ": " + pet.daysAlive() + " " + Emulator.getTexts().getValue("generic.pet.days.alive") + "\r" +
                                Emulator.getTexts().getValue("generic.pet.level") + ": " + pet.getLevel() + "\r" +
                                "\r" +
                                Emulator.getTexts().getValue("commands.generic.cmd_pet_info.stats") + "\r\n" +
                                Emulator.getTexts().getValue("generic.pet.scratches") + ": " + pet.getRespect() + "\r" +
                                Emulator.getTexts().getValue("generic.pet.energy") + ": " + pet.getEnergy() + "/" + PetManager.maxEnergy(pet.getLevel()) + "\r" +
                                Emulator.getTexts().getValue("generic.pet.happyness") + ": " + pet.getHappyness() + "\r" +
                                Emulator.getTexts().getValue("generic.pet.level.thirst") + ": " + ((Pet) pet).levelThirst + "\r" +
                                Emulator.getTexts().getValue("generic.pet.level.hunger") + ": " + ((Pet) pet).levelHunger + "\r" +
                                Emulator.getTexts().getValue("generic.pet.current_action") + ": " + (((Pet) pet).getTask() == null ? Emulator.getTexts().getValue("generic.nothing") :  ((Pet) pet).getTask().name()) + "\r" +
                                Emulator.getTexts().getValue("generic.can.walk") + ": " + (pet.getRoomUnit().canWalk() ? Emulator.getTexts().getValue("generic.yes") : Emulator.getTexts().getValue("generic.no")) + ""
                        ));
                    }

                    return true;
                }
            });
        }
        else
        {
            gameClient.getHabbo().whisper(Emulator.getTexts().getValue("commands.error.cmd_pet_info.pet_not_found"), RoomChatMessageBubbles.ALERT);
        }
        return true;
    }
}
