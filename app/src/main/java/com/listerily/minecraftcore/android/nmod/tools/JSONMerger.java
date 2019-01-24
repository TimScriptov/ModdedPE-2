package com.listerily.minecraftcore.android.nmod.tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class JSONMerger
{
    private String mSrc;
    private String mDist;

    public JSONMerger(String src, String src2)
    {
        this.mSrc = src;
        this.mDist = src2;
    }

    public String merge() throws JSONException
    {
        if (isJSONObject(mSrc) && isJSONObject(mDist))
        {
            return mergeObject(new JSONObject(mSrc), new JSONObject(mDist)).toString();
        }
        else if (isJSONArray(mSrc) && isJSONArray(mDist))
        {
            return mergeArray(new JSONArray(mSrc), new JSONArray(mDist)).toString();
        }
        else
            throw new JSONException("Json merging failed for the string provided is neither JSONArray nor JSONObject.");
    }

    private static boolean isJSONArray(String json)
    {
        try
        {
            new JSONArray(json);
            return true;
        }
        catch (JSONException e)
        {
            return false;
        }
    }

    private static boolean isJSONObject(String json)
    {
        try
        {
            new JSONObject(json);
            return true;
        }
        catch (JSONException e)
        {
            return false;
        }
    }

    private static JSONObject mergeObject(JSONObject object1, JSONObject object2) throws JSONException
    {
        Iterator iterator = object2.keys();
        for (; iterator.hasNext(); )
        {
            String name = (String) iterator.next();
            judgeTypeAndPut(object1, object2, name);
        }
        return object1;
    }

    private static JSONArray mergeArray(JSONArray array1, JSONArray array2) throws JSONException
    {
        for (int index = 0; index < array2.length(); ++index)
        {
            judgeTypeAndPut(array1, array2, index);
        }
        return array1;
    }


    private static void judgeTypeAndPut(JSONArray array1, JSONArray array2, int index) throws JSONException
    {
        if (isTypeJSONArray(array2, index))
        {
            array1.put(array2.getJSONArray(index));
        }
        else if (isTypeJSONObject(array2, index))
        {
            array1.put(array2.getJSONObject(index));
        }
        else if (isTypeJSONString(array2, index))
        {
            array1.put(array2.getString(index));
        }
        else if (isTypeJSONInteger(array2, index))
        {
            array1.put(array2.getInt(index));
        }
        else
            throw new JSONException("ERROR: CANNOT JUDGE ITEM TYPE.");
    }

    private static void judgeTypeAndPut(JSONObject object1, JSONObject object2, String key) throws JSONException
    {
        if (isTypeJSONArray(object2, key))
        {
            if (object1.has(key))
            {
                object1.put(key, mergeArray(object1.getJSONArray(key), object2.getJSONArray(key)));
            }
            else
            {
                object1.put(key, object2.getJSONArray(key));
            }
        }
        else if (isTypeJSONObject(object2, key))
        {
            if (object1.has(key))
            {
                object1.put(key, mergeObject(object1.getJSONObject(key), object2.getJSONObject(key)));
            }
            else
            {
                object1.put(key, object2.getJSONObject(key));
            }
        }
        else if (isTypeJSONString(object2, key))
        {
            object1.put(key, object2.getString(key));
        }
        else if (isTypeJSONInteger(object2, key))
        {
            object1.put(key, object2.getInt(key));
        }
        else
            throw new JSONException("ERROR:CANNOT JUDGE ITEM TYPE.");
    }

    private static boolean isTypeJSONArray(JSONObject src, String key)
    {
        try
        {
            src.getJSONArray(key);
            return true;
        }
        catch (JSONException jsonE)
        {
            return false;
        }
    }

    private static boolean isTypeJSONObject(JSONObject src, String key)
    {
        try
        {
            src.getJSONObject(key);
            return true;
        }
        catch (JSONException jsonE)
        {
            return false;
        }
    }

    private static boolean isTypeJSONString(JSONObject src, String key)
    {
        try
        {
            src.getString(key);
            return true;
        }
        catch (JSONException jsonE)
        {
            return false;
        }
    }

    private static boolean isTypeJSONInteger(JSONObject src, String key)
    {
        try
        {
            src.getInt(key);
            return true;
        }
        catch (JSONException jsonE)
        {
            return false;
        }
    }

    private static boolean isTypeJSONArray(JSONArray src, int index)
    {
        try
        {
            src.getJSONArray(index);
            return true;
        }
        catch (JSONException jsonE)
        {
            return false;
        }
    }

    private static boolean isTypeJSONObject(JSONArray src, int index)
    {
        try
        {
            src.getJSONObject(index);
            return true;
        }
        catch (JSONException jsonE)
        {
            return false;
        }
    }

    private static boolean isTypeJSONString(JSONArray src, int index)
    {
        try
        {
            src.getString(index);
            return true;
        }
        catch (JSONException jsonE)
        {
            return false;
        }
    }

    private static boolean isTypeJSONInteger(JSONArray src, int index)
    {
        try
        {
            src.getInt(index);
            return true;
        }
        catch (JSONException jsonE)
        {
            return false;
        }
    }
}
